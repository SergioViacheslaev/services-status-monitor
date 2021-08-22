package com.servicesmonitor.servicesstatusmonitorservice.service.impl

import com.fasterxml.jackson.databind.ObjectMapper
import com.servicesmonitor.servicesstatusmonitorservice.model.ServiceStatus
import com.servicesmonitor.servicesstatusmonitorservice.repo.MonitoredServiceRepository
import com.servicesmonitor.servicesstatusmonitorservice.service.ServiceMonitoring
import com.servicesmonitor.servicesstatusmonitorservice.service.StatusUpdate
import com.servicesmonitor.servicesstatusmonitorservice.service.websocket.WebSocketMessage
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import org.springframework.web.client.ResourceAccessException

/**
 * Requests and updates services statuses,
 * sends updated status to front via webSocket.
 */
@Service
class ServiceStatusUpdateService(
    val monitoringService: ServiceMonitoring,
    val monitoredServiceRepo: MonitoredServiceRepository,
    val messageService: WebSocketMessage,
) : StatusUpdate {
    private val logger = LoggerFactory.getLogger(javaClass)
    private val SERVICE_STATUS_INFO_TOPIC = "/topic/servicesStatusInfo"
    private val mapper = ObjectMapper()

    override fun updateServicesStatus() {
        monitoredServiceRepo.findAll().forEach {
            val lastServiceStatus = it.serviceStatusData.serviceStatus

            //Requesting service actual status
            try {
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
            } catch (e: ResourceAccessException) {
                logger.warn("Service ${it.serviceName} is not responding !")
                it.serviceStatusData.serviceStatus = ServiceStatus.OFFLINE
                monitoredServiceRepo.save(it)
            } finally {
                //Send updated status to front via websocket
                messageService.sendMessage(
                    SERVICE_STATUS_INFO_TOPIC, mapper.writeValueAsString(it.toDto())
                )
            }

        }
    }
}
