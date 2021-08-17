package com.servicesmonitor.servicesstatusmonitorservice.service.impl

import com.servicesmonitor.servicesstatusmonitorservice.repo.MonitoredServiceRepository
import com.servicesmonitor.servicesstatusmonitorservice.service.ServiceMonitoring
import com.servicesmonitor.servicesstatusmonitorservice.service.StatusUpdate
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service

@Service
class ServiceStatusUpdateService(
    val monitoringService: ServiceMonitoring,
    val monitoredServiceRepo: MonitoredServiceRepository
) : StatusUpdate {
    private val logger = LoggerFactory.getLogger(javaClass)

    override fun updateServiceStatus() {
        monitoredServiceRepo.findAll().forEach {
            val lastServiceStatus = it.serviceStatusData.serviceStatus
            //Requesting service actual status
            val actualServiceStatusData = monitoringService.requestServiceStatus(it.serviceStatusURL)

            if (actualServiceStatusData != null) {
                val actualServiceStatus = actualServiceStatusData.serviceStatus
                //Update if service status changed
                if (lastServiceStatus != actualServiceStatus) {
                    it.serviceStatusData = actualServiceStatusData
                    monitoredServiceRepo.save(it)
                    logger.info("Service ${it.serviceName} status changed to $actualServiceStatus")
                }
            }


        }


    }


}
