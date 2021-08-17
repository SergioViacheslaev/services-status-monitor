package com.servicesmonitor.servicesstatusmonitorservice.dto


data class MonitoredServiceDto(
    val serviceName: String,
    val usedMemoryPercentage: String,
    /** Short service status: UP, DOWN, OFFLINE */
    val shortStatus: String,
    /** Full description of current status including errors */
    val fullStatus: String,
)
