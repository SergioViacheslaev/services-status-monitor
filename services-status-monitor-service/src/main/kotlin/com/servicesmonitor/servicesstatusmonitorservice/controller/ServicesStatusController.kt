package com.servicesmonitor.servicesstatusmonitorservice.controller

import com.servicesmonitor.servicesstatusmonitorservice.service.ServiceMonitoring
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping


@RequestMapping("/services-monitor")
@Controller
class ServicesStatusController(
    val servicesMonitoringService: ServiceMonitoring
) {

    @GetMapping("/services-info")
    fun showServicesInfo(model: Model): String {
        val monitoredServicesDto = servicesMonitoringService.findAllMonitoredServices().map { it.toDto() }.toList()
        model.addAttribute("monitoredServices", monitoredServicesDto)

        return "services-info"
    }
}
