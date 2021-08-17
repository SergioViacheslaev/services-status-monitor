package com.servicesmonitor.servicesstatusmonitorservice.model

import com.servicesmonitor.servicesstatusmonitorservice.dto.MonitoredServiceDto
import org.springframework.data.mongodb.core.mapping.Document

/**
 * Monitored service data
 */
@Document("monitored-services")
data class MonitoredService(
    val serviceName: String,
    val serviceStatusURL: String,
    val serviceStatusData: ServiceStatusData
) {
    fun toDto() = MonitoredServiceDto(
        serviceName = serviceName,
        usedMemoryPercentage = serviceStatusData.jvmTotalMemory.toString(),
        shortStatus = serviceStatusData.serviceStatus.name,
        fullStatus = if (serviceStatusData.exceptionMessages.isNotEmpty()) {
            serviceStatusData.exceptionMessages
                .reduce { statusMessage, exceptionMessage -> statusMessage + exceptionMessage }
                .toString()
        } else {
            "Service works fine"
        }
    )
}



