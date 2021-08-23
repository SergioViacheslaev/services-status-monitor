package com.servicesmonitor.servicesstatusmonitorservice.controller

import com.servicesmonitor.servicesstatusmonitorservice.service.ServiceMonitoring
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.servlet.ModelAndView

/**
 * Returns status info page
 */
@RequestMapping("/services-monitor")
@Controller
class ServicesStatusController(
    val servicesMonitoringService: ServiceMonitoring
) {

    @GetMapping("/services/status")
    fun showServicesStatus(): ModelAndView {
        val monitoredServicesDto = servicesMonitoringService.findAllMonitoredServices().map { it.toDto() }.toList()
        return ModelAndView("services-status-page", "monitoredServices", monitoredServicesDto)
    }
}
