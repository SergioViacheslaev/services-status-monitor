package com.servisesstatus.processingservice

import com.servisesstatus.processingservice.confg.ServiceProperties
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.runApplication


@SpringBootApplication
@EnableConfigurationProperties(ServiceProperties::class)
class ProcessingServiceApplication

fun main(args: Array<String>) {
    runApplication<ProcessingServiceApplication>(*args)
}


