package com.servisesstatus.weatherservice.controller

import com.fasterxml.jackson.databind.ObjectMapper
import com.servisesstatus.weatherservice.dto.ServiceStatusDto
import com.servisesstatus.weatherservice.model.ServiceStatus
import com.servisesstatus.weatherservice.services.impl.ServiceStatusService
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.BDDMockito.given
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.junit.jupiter.MockitoExtension
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultHandlers.print
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.content
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import org.springframework.test.web.servlet.setup.MockMvcBuilders

@ExtendWith(MockitoExtension::class)
internal class ServiceStatusControllerTest {

    private val SERVICE_STATUS_URL = "/weather-service/status"
    private val mapper = ObjectMapper()

    @Mock
    lateinit var serviceStatusService: ServiceStatusService

    @InjectMocks
    lateinit var serviceStatusController: ServiceStatusController

    private lateinit var mockMvc: MockMvc

    @BeforeEach
    fun setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(serviceStatusController).build()
    }

    @Test
    fun getServiceStatus() {
        val serviceStatusDto = ServiceStatusDto(
            ServiceStatus.UP,
            "999",
            "111",
            emptyList()
        )
        val serviceStatusDtoJson = mapper.writeValueAsString(serviceStatusDto)
        given(serviceStatusService.getServiceStatusDto()).willReturn(serviceStatusDto)


        mockMvc.perform(get(SERVICE_STATUS_URL))
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(content().json(serviceStatusDtoJson))
            .andDo(print())
            .andExpect(status().isOk)
    }
}
