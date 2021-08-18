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
        usedMemoryPercentage = getUsedMemoryPercentage(),
        shortStatus = serviceStatusData.serviceStatus.name,
        fullStatus = getFullStatus()
    )

    private fun getUsedMemoryPercentage(): String {
        val jvmTotalMemory = serviceStatusData.jvmTotalMemory
        val jvmFreeMemory = serviceStatusData.jvmFreeMemory
        return String.format("%.2f", (100 * (jvmTotalMemory - jvmFreeMemory)).toDouble() / jvmTotalMemory)
    }

    private fun getFullStatus() = when (serviceStatusData.exceptionMessages.isNotEmpty()) {
        true -> serviceStatusData.exceptionMessages
            .reduce { statusMessage, exceptionMessage -> statusMessage + exceptionMessage }
            .toString()
        else -> ""
    }

}



