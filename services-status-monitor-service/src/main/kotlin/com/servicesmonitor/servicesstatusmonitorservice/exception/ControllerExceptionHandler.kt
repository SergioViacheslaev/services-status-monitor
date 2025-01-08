package com.servicesmonitor.servicesstatusmonitorservice.exception

import com.servicesmonitor.servicesstatusmonitorservice.controller.ServiceRegistrationController
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.http.converter.HttpMessageNotReadableException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestControllerAdvice


@RestControllerAdvice(assignableTypes = [ServiceRegistrationController::class])
class ControllerExceptionHandler {
    private val logger = LoggerFactory.getLogger(javaClass)

    @ExceptionHandler(value = [HttpMessageNotReadableException::class])
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    fun handleServiceRegistrationDataParseError() {
        logger.warn("Service registration data JSON parse error")
    }
}
