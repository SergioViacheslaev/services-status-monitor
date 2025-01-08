package com.servicesmonitor.servicesstatusmonitorservice.util

import com.servicesmonitor.servicesstatusmonitorservice.dto.MonitoredServiceDto
import com.servicesmonitor.servicesstatusmonitorservice.model.MonitoredService
import com.servicesmonitor.servicesstatusmonitorservice.model.ServiceStatusData
import org.apache.logging.log4j.util.Strings.EMPTY
import org.springframework.stereotype.Component

@Component
class MonitoredServiceDtoConverter {

    fun toDto(service: MonitoredService) = MonitoredServiceDto(
        service.serviceName,
        getUsedMemoryPercentage(service.serviceStatusData),
        service.serviceStatusData.serviceStatus.name,
        getFullStatus(service.serviceStatusData)
    )

    private fun getUsedMemoryPercentage(serviceStatusData: ServiceStatusData): String {
        val jvmTotalMemory = serviceStatusData.jvmTotalMemory
        val jvmFreeMemory = serviceStatusData.jvmFreeMemory
        return String.format("%.2f", (100 * (jvmTotalMemory - jvmFreeMemory)).toDouble() / jvmTotalMemory)
    }

    private fun getFullStatus(serviceStatusData: ServiceStatusData) =
        when (serviceStatusData.exceptionMessages.isNotEmpty()) {
            true -> serviceStatusData.exceptionMessages
                .reduce { statusMessage, exceptionMessage -> statusMessage + exceptionMessage }
                .toString()

            else -> EMPTY
        }
}
