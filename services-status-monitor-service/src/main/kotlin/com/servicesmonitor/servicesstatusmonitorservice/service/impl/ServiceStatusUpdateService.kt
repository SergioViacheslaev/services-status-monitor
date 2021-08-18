package com.servicesmonitor.servicesstatusmonitorservice.service.impl

import com.fasterxml.jackson.databind.ObjectMapper
import com.servicesmonitor.servicesstatusmonitorservice.repo.MonitoredServiceRepository
import com.servicesmonitor.servicesstatusmonitorservice.service.ServiceMonitoring
import com.servicesmonitor.servicesstatusmonitorservice.service.StatusUpdate
import com.servicesmonitor.servicesstatusmonitorservice.service.websocket.WebSocketMessage
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service

@Service
class ServiceStatusUpdateService(
    val monitoringService: ServiceMonitoring,
    val monitoredServiceRepo: MonitoredServiceRepository,
    val messageService: WebSocketMessage,
) : StatusUpdate {
    private val SERVICE_STATUS_INFO_TOPIC = "/topic/servicesStatusInfo"
    private val mapper = ObjectMapper()
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

                    //Send updated status to front via websocket
                    messageService.sendMessage(
                        SERVICE_STATUS_INFO_TOPIC,
                        mapper.writeValueAsString(it.toDto())
                    )
                }
            }
        }
    }
}
