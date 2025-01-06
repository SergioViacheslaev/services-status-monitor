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
import org.mockito.BDDMockito.given
import org.mockito.BDDMockito.then
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.junit.jupiter.MockitoExtension
import org.springframework.boot.web.client.RestTemplateBuilder
import org.springframework.web.client.RestTemplate


@ExtendWith(MockitoExtension::class)
internal class ServicesMonitoringServiceTest {
    val serviceRegistrationData = ServiceRegistrationData(
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
        //GIVEN
        val newMonitoredService = MonitoredService(
            ObjectId.get().toString(),
            serviceRegistrationData.serviceName,
            serviceRegistrationData.serviceStatusURL,
            ServiceStatusData(ServiceStatus.REGISTERED)
        )
        given(monitoredServiceRepo.save(any(MonitoredService::class.java))).willReturn(newMonitoredService)

        //WHEN
        val registeredMonitoredService =
            servicesMonitoringService.registerMonitoredService(serviceRegistrationData)

        //THEN
        then(monitoredServiceRepo).should().save(any(MonitoredService::class.java))
        assertThat(newMonitoredService).isEqualTo(registeredMonitoredService)
    }

    @Test
    fun `Finds all monitored services`() {
        //GIVEN
        val expectedMonitoredServices = listOf(
            MonitoredService(
                ObjectId.get().toString(),
                serviceRegistrationData.serviceName,
                serviceRegistrationData.serviceStatusURL,
                ServiceStatusData(ServiceStatus.REGISTERED)
            )
        )
        given(monitoredServiceRepo.findAll()).willReturn(expectedMonitoredServices)

        //WHEN
        val actualMonitoredServices = servicesMonitoringService.findAllMonitoredServices()

        //THEN
        then(monitoredServiceRepo).should().findAll()
        assertThat(expectedMonitoredServices).isEqualTo(actualMonitoredServices)
    }

}
