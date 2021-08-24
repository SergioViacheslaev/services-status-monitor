package com.servicesmonitor.servicesstatusmonitorservice.model

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

/**
 * Monitored service data
 */
@Document("monitored-services")
data class MonitoredService(
    @Id
    val id: String,
    val serviceName: String = "",
    val serviceStatusURL: String = "",
    var serviceStatusData: ServiceStatusData
)



