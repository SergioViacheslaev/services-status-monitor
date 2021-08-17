package com.servicesmonitor.servicesstatuschecker.controller

import com.servicesmonitor.servicesstatuschecker.model.ServiceRegistrationData
import com.servicesmonitor.servicesstatuschecker.service.ServiceMonitoring
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping("/services-monitor")
class ServiceRegistrationController(
    val servicesMonitoringService: ServiceMonitoring
) {
    private val logger = LoggerFactory.getLogger(javaClass)

    @PostMapping("/services-reg")
    fun handleRegistranionServiceRequest(@RequestBody serviceRegistrationData: ServiceRegistrationData): ResponseEntity<String> {
        logger.info("Received registration request from ${serviceRegistrationData.serviceName}")
        servicesMonitoringService.registerMonitoredService(serviceRegistrationData)

        return ResponseEntity(HttpStatus.OK)
    }
}
