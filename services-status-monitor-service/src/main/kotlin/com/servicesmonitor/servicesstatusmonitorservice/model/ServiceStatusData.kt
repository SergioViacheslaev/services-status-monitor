package com.servicesmonitor.servicesstatusmonitorservice.model

/**
 * Additional info about service status.
 * Receive as JSON from services via GET: /{service-name}/status
 */
data class ServiceStatusData(
    val serviceStatus: ServiceStatus = ServiceStatus.OFFLINE,
    val jvmTotalMemory: Long = 0,
    val jvmFreeMemory: Long = 0,
    val exceptionMessages: List<String> = emptyList()
)
