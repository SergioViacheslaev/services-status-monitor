package com.servisesstatus.processingservice.controller

import com.servisesstatus.processingservice.dto.ServiceStatusDto
import com.servisesstatus.processingservice.model.ServiceStatus
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
internal class ServiceStatusControllerIT {

    private val SERVICE_STATUS_URL = "/processing-service/status"

    @Autowired
    lateinit var restTemplate: TestRestTemplate

    @Test
    fun getServiceStatus() {
        val serviceStatusDto = restTemplate.getForObject(SERVICE_STATUS_URL, ServiceStatusDto::class.java)

        assertThat(serviceStatusDto.serviceStatus).isEqualTo(ServiceStatus.UP)
    }
}
