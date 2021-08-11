package com.servicesmonitor.servicesstatuschecker

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class ServicesStatusCheckerApplication

fun main(args: Array<String>) {
    runApplication<ServicesStatusCheckerApplication>(*args)
}
