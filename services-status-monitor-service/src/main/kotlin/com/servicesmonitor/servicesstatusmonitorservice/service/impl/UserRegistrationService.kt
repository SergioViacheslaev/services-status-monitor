package com.servicesmonitor.servicesstatusmonitorservice.service.impl

import com.servicesmonitor.servicesstatusmonitorservice.dto.UserDto
import com.servicesmonitor.servicesstatusmonitorservice.service.UserRegistration
import org.springframework.stereotype.Service


@Service
class UserRegistrationService : UserRegistration {
    private val SUCCESS_REGISTRATION_MESSAGE = "is successfully registered ! You may now login"
    private val PASSWORDS_NOT_MATCH = "Passwords not match, type again !"

    override fun register(userDto: UserDto): String {
        if (!isPasswordValid(userDto.password, userDto.matchingPassword)) {
            return PASSWORDS_NOT_MATCH
        }
        return "${userDto.email} $SUCCESS_REGISTRATION_MESSAGE"
    }

    private fun isPasswordValid(password: String, matchingPassword: String): Boolean {
        return password == matchingPassword
    }
}
