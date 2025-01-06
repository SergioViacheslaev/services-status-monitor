package com.servisesstatus.processingservice.services.impl

import com.servisesstatus.processingservice.confg.ServiceProperties
import com.servisesstatus.processingservice.dto.RegistrationRequestDto
import com.servisesstatus.processingservice.services.ServiceMonitorRegistration
import org.slf4j.LoggerFactory
import org.springframework.boot.context.event.ApplicationReadyEvent
import org.springframework.context.event.EventListener
import org.springframework.http.HttpEntity
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpMethod
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import org.springframework.web.client.ResourceAccessException
import org.springframework.web.client.RestTemplate


/**
 *  Sends registration request to Service Status Monitor
 */
@Service
class ServicesMonitorRegistrationService(
    val restTemplate: RestTemplate,
    val properties: ServiceProperties
) : ServiceMonitorRegistration {
    private val logger = LoggerFactory.getLogger(javaClass)

    @EventListener(ApplicationReadyEvent::class)
    override fun register() {
        val registrationDto = RegistrationRequestDto(properties.serviceName, properties.serviceStatusURL)

        val headers = HttpHeaders()
        headers.contentType = MediaType.APPLICATION_JSON
        val requestEntity: HttpEntity<RegistrationRequestDto> =
            HttpEntity<RegistrationRequestDto>(registrationDto, headers)
        try {
            val response: ResponseEntity<String> = restTemplate.exchange(
                properties.servicesMonitorRegistrationURL, HttpMethod.POST, requestEntity,
                String::class.java
            )
            logger.info("Sending registration request to ${properties.servicesMonitorRegistrationURL}")
            if (response.statusCode == HttpStatus.OK) {
                logger.info("${properties.serviceName} registered successfully")
            } else {
                logger.warn("${properties.serviceName} registration failed, error code: ${response.statusCodeValue} ")
            }
        } catch (e: ResourceAccessException) {
            logger.warn("Couldn't connect to Services Status Monitor !")
        }
    }
}
