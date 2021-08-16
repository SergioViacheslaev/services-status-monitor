package com.servisesstatus.weatherservice.confg

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.ConstructorBinding

@ConstructorBinding
@ConfigurationProperties(prefix = "weather-service")
data class ServiceProperties(
    val serviceName: String,
    val serviceStatusURL: String,
    val servicesMonitorRegistrationURL: String
)
