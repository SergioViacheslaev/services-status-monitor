package com.servicesmonitor.servicesstatusmonitorservice.controller

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.servicesmonitor.servicesstatusmonitorservice.model.ServiceRegistrationData
import com.servicesmonitor.servicesstatusmonitorservice.service.ServiceMonitoring
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.mockito.Mockito.doNothing
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.post
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status


@SpringBootTest
@AutoConfigureMockMvc
class ServiceRegistrationControllerTest {
    companion object {
        const val SERVICE_NAME = "Test-service"
        const val SERVICE_STATUS_URL = "http://localhost:8082/weather-service/status"
        const val SERVICE_REGISTRATION_URL = "http://localhost:8080/services-monitor/services-reg"
    }

    @Autowired
    private lateinit var mockMvc: MockMvc

    @MockBean
    private lateinit var servicesMonitoringService: ServiceMonitoring
    private val objectMapper = jacksonObjectMapper()


    @Test
    @DisplayName("Service registers successfully")
    fun serviceRegistrationSuccessTest() {
        val serviceRegistrationData = ServiceRegistrationData(SERVICE_NAME, SERVICE_STATUS_URL)
        doNothing().`when`(servicesMonitoringService).registerMonitoredService(serviceRegistrationData)

        mockMvc.post(SERVICE_REGISTRATION_URL) {
            content = objectMapper.writeValueAsString(serviceRegistrationData)
            contentType = MediaType.APPLICATION_JSON
        }.andDo {
            print()
        }.andExpect {
            status().isOk
        }
    }


}
