package com.servisesstatus.processingservice.services.impl

import com.servisesstatus.processingservice.services.ServiceHealth
import kotlinx.coroutines.delay
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service

/**
 * Health check service, imitating statuses check in parallel
 * Return empty list if everything is OK or list of exceptions
 */
@Service
class HealthCheckService : ServiceHealth {
    private val logger = LoggerFactory.getLogger(javaClass)
    override suspend fun checkDataBaseConnections(): List<String> {
        delay(500)
        logger.info("DB connections check is completed")
        return emptyList()
    }

    override suspend fun checkAmazonCloudAPI(): List<String> {
        delay(500)
        logger.info("AmazonCloud API check is completed")
        return emptyList()
    }

    override suspend fun checkMessagingQueue(): List<String> {
        delay(500)
        logger.info("Messaging queue check is completed")
        return emptyList()
    }
}
