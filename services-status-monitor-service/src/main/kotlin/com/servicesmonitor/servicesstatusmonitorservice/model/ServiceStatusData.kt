package com.servicesmonitor.servicesstatusmonitorservice.model

/**
 * Service status data,
 * receive as JSON from services via GET: /{service-name}/status
 */
data class ServiceStatusData(
    var serviceStatus: ServiceStatus = ServiceStatus.OFFLINE,
    val jvmTotalMemory: Long = 0,
    val jvmFreeMemory: Long = 0,
    val exceptionMessages: List<String> = emptyList()
)
