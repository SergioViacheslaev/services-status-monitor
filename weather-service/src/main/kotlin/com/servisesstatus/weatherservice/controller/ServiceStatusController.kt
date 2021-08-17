package com.servisesstatus.weatherservice.controller

import com.servisesstatus.weatherservice.dto.ServiceStatusDto
import com.servisesstatus.weatherservice.services.ServiceStatusData
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/weather-service")
class ServiceStatusController(
    val serviceStatusService: ServiceStatusData
) {

    @GetMapping("/status")
    fun getServiceStatus(): ServiceStatusDto {
        return serviceStatusService.getServiceStatusDto()
    }
}
