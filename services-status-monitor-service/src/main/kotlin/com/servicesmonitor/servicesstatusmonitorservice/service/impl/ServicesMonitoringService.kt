package com.servicesmonitor.servicesstatusmonitorservice.service.impl

import com.servicesmonitor.servicesstatusmonitorservice.model.MonitoredService
import com.servicesmonitor.servicesstatusmonitorservice.model.ServiceRegistrationData
import com.servicesmonitor.servicesstatusmonitorservice.model.ServiceStatus
import com.servicesmonitor.servicesstatusmonitorservice.model.ServiceStatusData
import com.servicesmonitor.servicesstatusmonitorservice.repo.MonitoredServiceRepository
import com.servicesmonitor.servicesstatusmonitorservice.service.ServiceMonitoring
import org.bson.types.ObjectId
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import org.springframework.web.client.RestTemplate

/**
 * Performs service registration, saves to DB
 */
@Service
class ServicesMonitoringService(
    val monitoredServiceRepo: MonitoredServiceRepository,
    val restTemplate: RestTemplate
) : ServiceMonitoring {
    private val logger = LoggerFactory.getLogger(javaClass)

    override fun registerMonitoredService(serviceRegistrationData: ServiceRegistrationData): MonitoredService {
        val monitoredService = MonitoredService(
            ObjectId.get().toString(),
            serviceRegistrationData.serviceName,
            serviceRegistrationData.serviceStatusURL,
            ServiceStatusData(ServiceStatus.REGISTERED)
        )
        //Check if service already registered
        val existingService = monitoredServiceRepo.findByServiceName(monitoredService.serviceName)
        return if (existingService != null) {
            logger.info("Service ${monitoredService.serviceName} is already registered.")
            existingService;
        } else {
            val registeredService = monitoredServiceRepo.save(monitoredService)
            logger.info("Service ${monitoredService.serviceName} is registered.")
            registeredService;
        }

    }

    override fun requestServiceStatus(serviceStatusURL: String): ServiceStatusData? {
        return restTemplate.getForObject(serviceStatusURL, ServiceStatusData::class.java)
    }

    override fun findAllMonitoredServices(): List<MonitoredService> {
        return monitoredServiceRepo.findAll()
    }
}
