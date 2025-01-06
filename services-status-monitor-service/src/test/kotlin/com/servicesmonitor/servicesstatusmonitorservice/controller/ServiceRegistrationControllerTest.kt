package com.servicesmonitor.servicesstatusmonitorservice.controller

import com.fasterxml.jackson.databind.ObjectMapper
import com.servicesmonitor.servicesstatusmonitorservice.model.ServiceRegistrationData
import com.servicesmonitor.servicesstatusmonitorservice.service.ServiceMonitoring
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.BDDMockito.then
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.junit.jupiter.MockitoExtension
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultHandlers.print
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import org.springframework.test.web.servlet.setup.MockMvcBuilders

@ExtendWith(MockitoExtension::class)
internal class ServiceRegistrationControllerTest {
    private val SERVICE_REGISTRATION_URL = "/services-monitor/services/register"
    private val SERVICE_REGISTRATION_DATA = ServiceRegistrationData(
        "Test-service",
        "http://localhost:8083/test-service/status"
    )

    private val mapper = ObjectMapper()

    @Mock
    lateinit var servicesMonitoringService: ServiceMonitoring

    @InjectMocks
    lateinit var registrationController: ServiceRegistrationController

    private lateinit var mockMvc: MockMvc

    @BeforeEach
    fun setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(registrationController).build()
    }

    @Test
    fun `Handles service registration request successfully`() {
        //GIVEN
        val serviceRegistrationDataJson = mapper.writeValueAsString(SERVICE_REGISTRATION_DATA)

        mockMvc.perform(
            post(SERVICE_REGISTRATION_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(serviceRegistrationDataJson)
        )
            .andDo(print())
            .andExpect(status().isOk)


        //THEN
        then(servicesMonitoringService).should().registerMonitoredService(SERVICE_REGISTRATION_DATA)
    }

    @Test
    fun `Handles service registration bad request`() {
        //GIVEN
        val serviceRegistrationDataJson = mapper.writeValueAsString("INCORRECT REGISTRATION DATA")

        mockMvc.perform(
            post(SERVICE_REGISTRATION_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(serviceRegistrationDataJson)
        )
            .andDo(print())
            .andExpect(status().isBadRequest)


        //THEN
        then(servicesMonitoringService).shouldHaveNoInteractions()
    }

}
