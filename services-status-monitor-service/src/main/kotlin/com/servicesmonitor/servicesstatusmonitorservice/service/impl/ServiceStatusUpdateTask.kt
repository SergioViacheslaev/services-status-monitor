package com.servicesmonitor.servicesstatusmonitorservice.service.impl

import com.servicesmonitor.servicesstatusmonitorservice.service.ServiceMonitoring
import com.servicesmonitor.servicesstatusmonitorservice.service.StatusUpdateTask
import org.slf4j.LoggerFactory
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Service
import org.springframework.web.client.ResourceAccessException

@Service
class ServiceStatusUpdateTask(
    val servicesMonitoringService: ServiceMonitoring
) : StatusUpdateTask {
    private val logger = LoggerFactory.getLogger(ServiceStatusUpdateTask::class.java)

    @Scheduled(fixedRate = 10_000)
    override fun updateServiceStatusTask() {
        logger.info("Status updates task is performed")
        servicesMonitoringService.findAllMonitoredServices()
            .forEach {
                println(
                    try {
                    servicesMonitoringService.requestServiceStatus(it.serviceStatusURL)
                    }catch (e: ResourceAccessException) {
                        logger.info("Service is OFFLINE")
                    }
                )
            }
    }
}
