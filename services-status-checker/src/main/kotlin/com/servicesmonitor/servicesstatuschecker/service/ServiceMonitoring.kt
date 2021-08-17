package com.servicesmonitor.servicesstatuschecker.service

import com.servicesmonitor.servicesstatuschecker.model.MonitoredService
import com.servicesmonitor.servicesstatuschecker.model.ServiceRegistrationData


interface ServiceMonitoring {
    fun registerMonitoredService(serviceRegistrationData: ServiceRegistrationData)
    fun findAllMonitoredServices(): List<MonitoredService>
}
