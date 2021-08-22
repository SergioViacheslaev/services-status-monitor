package com.servisesstatus.processingservice.dto

import com.servisesstatus.processingservice.model.ServiceStatus

data class ServiceStatusDto(
    val serviceStatus: ServiceStatus = ServiceStatus.OFFLINE,
    val jvmTotalMemory: String = "",
    val jvmFreeMemory: String = "",
    val exceptionMessages: List<String> = emptyList()
)
