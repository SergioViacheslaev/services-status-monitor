package com.servicesmonitor.servicesstatusmonitorservice

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.scheduling.annotation.EnableScheduling

@SpringBootApplication
@EnableScheduling
class ServicesStatusCheckerApplication

fun main(args: Array<String>) {
    runApplication<ServicesStatusCheckerApplication>(*args)
}