package com.servisesstatus.weatherservice.controller

import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.Mock
import org.mockito.junit.jupiter.MockitoExtension
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import org.springframework.test.web.servlet.setup.MockMvcBuilders


@ExtendWith(MockitoExtension::class)
internal class WeatherInfoControllerTest {

    private val WEATHER_INFO_URL = "/weather-service/weather-info"

    @Mock
    lateinit var weatherInfoController: WeatherInfoController

    private lateinit var mockMvc: MockMvc

    @BeforeEach
    fun setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(weatherInfoController).build()
    }

    @Test
    fun showWeatherInfo() {
        mockMvc.perform(get(WEATHER_INFO_URL)).andExpect(status().isOk)
    }
}
