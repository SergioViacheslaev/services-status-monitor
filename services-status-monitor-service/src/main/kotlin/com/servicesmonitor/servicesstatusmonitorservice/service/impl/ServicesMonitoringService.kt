package com.servicesmonitor.servicesstatusmonitorservice.service.impl

import com.servicesmonitor.servicesstatusmonitorservice.model.MonitoredService
import com.servicesmonitor.servicesstatusmonitorservice.model.ServiceRegistrationData
import com.servicesmonitor.servicesstatusmonitorservice.model.ServiceStatus
import com.servicesmonitor.servicesstatusmonitorservice.model.ServiceStatusData
import com.servicesmonitor.servicesstatusmonitorservice.repo.MonitoredServiceRepository
import com.servicesmonitor.servicesstatusmonitorservice.service.ServiceMonitoring
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import org.springframework.web.client.RestTemplate

@Service
class ServicesMonitoringService(
    val monitoredServiceRepo: MonitoredServiceRepository,
    val restTemplate: RestTemplate
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

    override fun requestServiceStatus(serviceStatusURL: String): ServiceStatusData? {
        return restTemplate.getForObject(serviceStatusURL, ServiceStatusData::class.java)
    }

    override fun findAllMonitoredServices(): List<MonitoredService> {
        return monitoredServiceRepo.findAll()
    }
}
