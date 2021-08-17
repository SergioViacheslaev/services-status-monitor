package com.servisesstatus.weatherservice.services.impl

import com.servisesstatus.weatherservice.dto.ServiceStatusDto
import com.servisesstatus.weatherservice.model.ServiceStatus
import com.servisesstatus.weatherservice.services.ServiceStatusData
import org.springframework.stereotype.Service

@Service
class ServiceStatusService : ServiceStatusData {

    override fun getServiceStatusDto() = ServiceStatusDto(
        getCurrentStatus(),
        Runtime.getRuntime().totalMemory().toString(),
        Runtime.getRuntime().freeMemory().toString(),
        emptyList()
    )

    private fun getCurrentStatus(): ServiceStatus {
        val rnds = (-1..1).random()
        return if (rnds > 0) {
            ServiceStatus.UP
        } else {
            ServiceStatus.DOWN
        }
    }

}
