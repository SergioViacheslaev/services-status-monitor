package com.servisesstatus.weatherservice.dto

import com.servisesstatus.weatherservice.model.ServiceStatus

/**
 * Additional info about service status.
 * Receive as JSON from services via GET: /{service-name}/status
 */
data class ServiceStatusDto(
    val serviceStatus: ServiceStatus =  ServiceStatus.OFFLINE,
    val jvmTotalMemory: String = "",
    val jvmFreeMemory: String = "",
    val exceptionMessages: List<String> = emptyList()
)
