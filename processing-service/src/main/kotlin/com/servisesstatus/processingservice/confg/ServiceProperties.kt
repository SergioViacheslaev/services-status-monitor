package com.servisesstatus.processingservice.confg

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.ConstructorBinding

@ConstructorBinding
@ConfigurationProperties(prefix = "processing-service")
data class ServiceProperties(
    val serviceName: String,
    val serviceStatusURL: String,
    val servicesMonitorRegistrationURL: String
)
