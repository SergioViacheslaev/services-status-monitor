package com.servicesmonitor.servicesstatusmonitorservice.controller

import com.servicesmonitor.servicesstatusmonitorservice.dto.MonitoredServiceDto
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping


@RequestMapping("/services-monitor")
@Controller
class ServicesStatusController {

    @GetMapping("/services-info")
    fun showServicesInfo(model: Model): String {
        val servicesDto = mutableListOf<MonitoredServiceDto>()
        servicesDto.add(MonitoredServiceDto("Weather-service",
            "15", "UP", "Works correctly"))
        servicesDto.add(MonitoredServiceDto("Tickets service",
            "90", "DOWN", "SQL errors"))

        model.addAttribute("monitoredServices", servicesDto)

        return "services-info"
    }
}
