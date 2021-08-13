package com.servicesmonitor.servicesstatuschecker.controller

import com.servicesmonitor.servicesstatuschecker.model.MonitoredService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping("/services-monitor")
class ServiceRegistrationController {

    @PostMapping("/services-reg")
    fun handleRegistranionServiceRequest(@RequestBody monitoredService: MonitoredService): ResponseEntity<String> {
        return ResponseEntity("Service registered", HttpStatus.OK)
    }
}
