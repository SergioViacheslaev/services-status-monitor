package com.servicesmonitor.servicesstatuschecker

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.servicesmonitor.servicesstatuschecker.model.MonitoredService
import com.servicesmonitor.servicesstatuschecker.model.ServiceRegistrationData
import com.servicesmonitor.servicesstatuschecker.model.ServiceStatusData
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.post
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status


@SpringBootTest
@AutoConfigureMockMvc
class ServiceRegistrationTest {
    companion object {
        const val SERVICE_NAME = "weather-service"
        const val SERVICE_STATUS_URL = "http://localhost:8082/weather-service/status"
        const val SERVICE_REGISTRATION_URL = "http://localhost:8080/services-monitor/services-reg"
    }

    @Autowired
    private lateinit var mockMvc: MockMvc
    private lateinit var monitoredService: MonitoredService
    private val objectMapper = jacksonObjectMapper()

    @BeforeEach
    fun setup() {
        monitoredService = MonitoredService(
            ServiceRegistrationData(SERVICE_NAME, SERVICE_STATUS_URL), ServiceStatusData()
        )
    }

    @Test
    @DisplayName("Service registers successfully")
    fun serviceRegistrationSuccessTest() {
        mockMvc.post(SERVICE_REGISTRATION_URL) {
            content = objectMapper.writeValueAsString(monitoredService)
            contentType = MediaType.APPLICATION_JSON
            accept = MediaType.APPLICATION_JSON
        }.andDo {
            print()
        }.andExpect {
            status().isOk
            content { contentType(MediaType.APPLICATION_JSON) }
            content { string("Service registered") }
        }
    }


}
