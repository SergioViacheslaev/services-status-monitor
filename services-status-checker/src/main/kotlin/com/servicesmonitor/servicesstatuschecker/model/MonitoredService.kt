package com.servicesmonitor.servicesstatuschecker.model

/**
 * Monitored service data: registration and status info
 */
data class MonitoredService(
    val registrationData: ServiceRegistrationData,
    val serviceStatusData: ServiceStatusData
)
