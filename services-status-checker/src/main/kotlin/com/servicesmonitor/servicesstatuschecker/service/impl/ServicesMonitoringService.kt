package com.servicesmonitor.servicesstatuschecker.service.impl

import com.servicesmonitor.servicesstatuschecker.model.MonitoredService
import com.servicesmonitor.servicesstatuschecker.model.ServiceRegistrationData
import com.servicesmonitor.servicesstatuschecker.model.ServiceStatus
import com.servicesmonitor.servicesstatuschecker.model.ServiceStatusData
import com.servicesmonitor.servicesstatuschecker.repo.MonitoredServiceRepository
import com.servicesmonitor.servicesstatuschecker.service.ServiceMonitoring
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service

@Service
class ServicesMonitoringService(
    val monitoredServiceRepo: MonitoredServiceRepository
) : ServiceMonitoring {
    private val logger = LoggerFactory.getLogger(javaClass)

    override fun registerMonitoredService(serviceRegistrationData: ServiceRegistrationData) {
        val monitoredService = MonitoredService(
            serviceRegistrationData.serviceName,
            serviceRegistrationData.serviceStatusURL,
            ServiceStatusData(ServiceStatus.REGISTERED)
        )
        monitoredServiceRepo.save(monitoredService)
        logger.info("Service ${monitoredService.serviceName} is registered.")
    }

    override fun findAllMonitoredServices(): List<MonitoredService> {
        return monitoredServiceRepo.findAll()
    }
}
