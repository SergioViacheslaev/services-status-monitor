package com.servicesmonitor.servicesstatusmonitorservice.service

import com.servicesmonitor.servicesstatusmonitorservice.model.MonitoredService
import com.servicesmonitor.servicesstatusmonitorservice.model.ServiceRegistrationData
import com.servicesmonitor.servicesstatusmonitorservice.model.ServiceStatusData


interface ServiceMonitoring {
    fun registerMonitoredService(serviceRegistrationData: ServiceRegistrationData): MonitoredService
    fun findAllMonitoredServices(): List<MonitoredService>
    fun requestServiceStatus(serviceStatusURL: String): ServiceStatusData?
}
