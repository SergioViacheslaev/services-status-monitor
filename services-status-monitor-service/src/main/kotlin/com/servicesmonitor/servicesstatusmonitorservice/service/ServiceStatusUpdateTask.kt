package com.servicesmonitor.servicesstatusmonitorservice.service

import org.slf4j.LoggerFactory
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Service

@Service
class ServiceStatusUpdateTask(
    val statusUpdateService: StatusUpdate
) {
    private val logger = LoggerFactory.getLogger(javaClass)

    @Scheduled(fixedRate = 10_000)
    fun updateServiceStatusTask() {
        logger.info("Status update task is performed")
        statusUpdateService.updateServiceStatus()
    }
}
