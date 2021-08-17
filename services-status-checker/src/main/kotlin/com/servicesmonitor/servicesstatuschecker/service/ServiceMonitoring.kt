package com.servicesmonitor.servicesstatuschecker.service

import com.servicesmonitor.servicesstatuschecker.model.MonitoredService
import com.servicesmonitor.servicesstatuschecker.model.ServiceRegistrationData
import com.servicesmonitor.servicesstatuschecker.model.ServiceStatusData


interface ServiceMonitoring {
    fun registerMonitoredService(serviceRegistrationData: ServiceRegistrationData)
    fun findAllMonitoredServices(): List<MonitoredService>
    fun requestServiceStatus(serviceStatusURL: String): ServiceStatusData?
}
