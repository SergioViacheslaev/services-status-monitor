package com.servisesstatus.weatherservice.services.impl

import com.servisesstatus.weatherservice.dto.ServiceStatusDto
import com.servisesstatus.weatherservice.model.ServiceStatus
import com.servisesstatus.weatherservice.services.ServiceStatusData
import org.springframework.stereotype.Service

@Service
class ServiceStatusService : ServiceStatusData {

    override fun getServiceStatusDto() = ServiceStatusDto(
        ServiceStatus.UP,
        Runtime.getRuntime().totalMemory().toString(),
        Runtime.getRuntime().freeMemory().toString(),
        emptyList()
    )

}
