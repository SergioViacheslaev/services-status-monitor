package com.servicesmonitor.servicesstatuschecker.model

import org.springframework.data.mongodb.core.mapping.Document

/**
 * Monitored service data
 */
@Document("monitored-services")
data class MonitoredService(
    val serviceName: String,
    val serviceStatusURL: String,
    val serviceStatusData: ServiceStatusData
)
