package com.servisesstatus.processingservice.services.impl

import com.servisesstatus.processingservice.dto.ServiceStatusDto
import com.servisesstatus.processingservice.model.ServiceStatus
import com.servisesstatus.processingservice.services.ServiceHealth
import com.servisesstatus.processingservice.services.ServiceStatusData
import kotlinx.coroutines.async
import kotlinx.coroutines.runBlocking
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import kotlin.time.ExperimentalTime
import kotlin.time.measureTime

@Service
class ServiceStatusService(
    val healthCheckService: ServiceHealth
) : ServiceStatusData {
    private val logger = LoggerFactory.getLogger(javaClass)
    private var serviceStatus: ServiceStatus = ServiceStatus.OFFLINE

    override fun getServiceStatusDto() = ServiceStatusDto(
        getCurrentStatus(),
        Runtime.getRuntime().totalMemory().toString(),
        Runtime.getRuntime().freeMemory().toString(),
        getServiceExceptions()
    )

    /**
     *  Dummy method to define service status,
     *  after all async checks completed.
     */
    @OptIn(ExperimentalTime::class)
    private fun getCurrentStatus(): ServiceStatus = runBlocking {
        var currentServiceStatus = ServiceStatus.UP
        val checkStatusTime = measureTime {
            val dbConnectionExceptions = async { healthCheckService.checkDataBaseConnections() }
            val amazonCloudApiExceptions = async { healthCheckService.checkAmazonCloudAPI() }
            val messaggingQueueExceptions = async { healthCheckService.checkMessagingQueue() }

            if (dbConnectionExceptions.await().isNotEmpty()
                || amazonCloudApiExceptions.await().isNotEmpty()
                || messaggingQueueExceptions.await().isNotEmpty()
            ) {
                currentServiceStatus = ServiceStatus.DOWN
            }
        }
        logger.info("Health check is completed in ${checkStatusTime.inWholeMilliseconds} ms")

        return@runBlocking currentServiceStatus
    }


    /**
     *  Dummy method to generate random exceptions messages
     */
    private fun getServiceExceptions(): List<String> {
        val exceptions = mutableListOf<String>()
        if (serviceStatus == ServiceStatus.DOWN) {
            when ((0..3).random()) {
                1 -> exceptions.add("Error connecting to DB")
                2 -> exceptions.add("Weather API not responding")
                3 -> exceptions.add("Security connection refused")
            }
        }
        return exceptions
    }

}
