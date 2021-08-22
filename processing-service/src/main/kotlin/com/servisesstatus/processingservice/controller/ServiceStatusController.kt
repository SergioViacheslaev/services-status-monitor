package com.servisesstatus.processingservice.controller

import com.servisesstatus.processingservice.dto.ServiceStatusDto
import com.servisesstatus.processingservice.services.ServiceStatusData
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/processing-service")
class ServiceStatusController(
    val serviceStatusService: ServiceStatusData
) {

    @GetMapping("/status")
    fun getServiceStatus(): ServiceStatusDto {
        return serviceStatusService.getServiceStatusDto()
    }
}
