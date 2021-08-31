package com.servicesmonitor.servicesstatusmonitorservice.controller

import com.servicesmonitor.servicesstatusmonitorservice.model.MonitoredService
import com.servicesmonitor.servicesstatusmonitorservice.model.ServiceStatus
import com.servicesmonitor.servicesstatusmonitorservice.model.ServiceStatusData
import com.servicesmonitor.servicesstatusmonitorservice.service.ServiceMonitoring
import com.servicesmonitor.servicesstatusmonitorservice.util.MonitoredServiceDtoConverter
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.BDDMockito.given
import org.mockito.BDDMockito.then
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.junit.jupiter.MockitoExtension
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.*
import org.springframework.test.web.servlet.setup.MockMvcBuilders

@ExtendWith(MockitoExtension::class)
internal class ServicesStatusControllerTest {
    private val SERVICES_STATUS_INFO_URL = "/services-monitor/services/status"

    @Mock
    lateinit var serviceMonitoringService: ServiceMonitoring

    @Mock
    lateinit var dtoConverter: MonitoredServiceDtoConverter

    @InjectMocks
    lateinit var servicesStatusController: ServicesStatusController

    private lateinit var mockMvc: MockMvc

    @BeforeEach
    fun setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(servicesStatusController).build()
    }

    @Test
    fun `Shows services status page`() {
        val monitoredService = MonitoredService(
            "1",
            "Test-service",
            "http://localhost:8083/test-service/status",
            ServiceStatusData(ServiceStatus.UP)
        )
        given(serviceMonitoringService.findAllMonitoredServices()).willReturn(listOf(monitoredService))
        given(dtoConverter.toDto(monitoredService)).willCallRealMethod()

        mockMvc.perform(get(SERVICES_STATUS_INFO_URL))
            .andExpect(status().isOk)
            .andExpect(model().attributeExists("monitoredServices"))
            .andExpect(view().name("services-status-page"))

        then(dtoConverter).should().toDto(monitoredService)
        then(serviceMonitoringService).should().findAllMonitoredServices()
    }
}
