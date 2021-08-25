package com.servicesmonitor.servicesstatusmonitorservice.controller

import com.servicesmonitor.servicesstatusmonitorservice.service.ServiceMonitoring
import com.servicesmonitor.servicesstatusmonitorservice.util.MonitoredServiceDtoConverter
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.servlet.ModelAndView
import springfox.documentation.annotations.ApiIgnore

/**
 * Returns status info page
 */
@RequestMapping("/services-monitor")
@ApiIgnore
@Controller
class ServicesStatusController(
    val servicesMonitoringService: ServiceMonitoring,
    val dtoConverter: MonitoredServiceDtoConverter
) {

    @GetMapping("/services/status")
    fun showServicesStatus(): ModelAndView {
        val monitoredServicesDto =
            servicesMonitoringService.findAllMonitoredServices().map { dtoConverter.toDto(it) }.toList()
        return ModelAndView("services-status-page", "monitoredServices", monitoredServicesDto)
    }
}
