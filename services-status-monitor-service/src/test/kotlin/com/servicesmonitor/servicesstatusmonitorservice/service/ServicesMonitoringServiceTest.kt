package com.servicesmonitor.servicesstatusmonitorservice.service

import com.servicesmonitor.servicesstatusmonitorservice.model.MonitoredService
import com.servicesmonitor.servicesstatusmonitorservice.model.ServiceRegistrationData
import com.servicesmonitor.servicesstatusmonitorservice.model.ServiceStatus
import com.servicesmonitor.servicesstatusmonitorservice.model.ServiceStatusData
import com.servicesmonitor.servicesstatusmonitorservice.repo.MonitoredServiceRepository
import com.servicesmonitor.servicesstatusmonitorservice.service.impl.ServicesMonitoringService
import org.assertj.core.api.Assertions.assertThat
import org.bson.types.ObjectId
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.ArgumentMatchers.any
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.jupiter.MockitoExtension
import org.springframework.boot.web.client.RestTemplateBuilder
import org.springframework.web.client.RestTemplate

@ExtendWith(MockitoExtension::class)
internal class ServicesMonitoringServiceTest {
    val SERVICE_REGISTRATION_DATA = ServiceRegistrationData(
        "Test-service",
        "http://localhost:8083/test-service/status"
    )

    @Mock
    lateinit var monitoredServiceRepo: MonitoredServiceRepository

    @Mock
    val restTemplate: RestTemplate = RestTemplateBuilder().build()

    @InjectMocks
    lateinit var servicesMonitoringService: ServicesMonitoringService

    @Test
    fun `Registers new monitoring service`() {
        val newMonitoredService = MonitoredService(
            ObjectId.get().toString(),
            SERVICE_REGISTRATION_DATA.serviceName,
            SERVICE_REGISTRATION_DATA.serviceStatusURL,
            ServiceStatusData(ServiceStatus.REGISTERED)
        )

        `when`(monitoredServiceRepo.save(any(MonitoredService::class.java))).thenReturn(newMonitoredService)

        val registeredMonitoredService = servicesMonitoringService.registerMonitoredService(SERVICE_REGISTRATION_DATA)
        assertThat(newMonitoredService).isEqualTo(registeredMonitoredService)
    }

    @Test
    fun `Finds all monitored services`() {
        val monitoredServices = listOf(
            MonitoredService(
                ObjectId.get().toString(),
                SERVICE_REGISTRATION_DATA.serviceName,
                SERVICE_REGISTRATION_DATA.serviceStatusURL,
                ServiceStatusData(ServiceStatus.REGISTERED)
            )
        )

        `when`(monitoredServiceRepo.findAll()).thenReturn(monitoredServices)
        assertThat(monitoredServices).isEqualTo(servicesMonitoringService.findAllMonitoredServices())
    }


}
