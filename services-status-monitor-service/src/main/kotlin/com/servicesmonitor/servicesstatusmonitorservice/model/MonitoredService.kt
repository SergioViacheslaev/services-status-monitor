package com.servicesmonitor.servicesstatusmonitorservice.model

import com.servicesmonitor.servicesstatusmonitorservice.dto.MonitoredServiceDto
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



