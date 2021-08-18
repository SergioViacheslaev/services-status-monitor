package com.servisesstatus.weatherservice.dto

import com.servisesstatus.weatherservice.model.ServiceStatus

data class ServiceStatusDto(
    val serviceStatus: ServiceStatus = ServiceStatus.OFFLINE,
    val jvmTotalMemory: String = "",
    val jvmFreeMemory: String = "",
    val exceptionMessages: List<String> = emptyList()
)
