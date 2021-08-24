package com.servicesmonitor.servicesstatusmonitorservice.service.impl

import com.servicesmonitor.servicesstatusmonitorservice.dto.UserDto
import com.servicesmonitor.servicesstatusmonitorservice.model.User
import com.servicesmonitor.servicesstatusmonitorservice.repo.UserRepository
import com.servicesmonitor.servicesstatusmonitorservice.service.UserRegistration
import org.bson.types.ObjectId
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service


@Service
class UserRegistrationService(
    val userRepository: UserRepository,
    val encoder: PasswordEncoder
) : UserRegistration {
    private val SUCCESS_REGISTRATION_MESSAGE = "is successfully registered ! You may now login"
    private val PASSWORDS_NOT_MATCH = "Passwords don't match, type again !"
    private val EMAIL_ALREADY_REGISTERED = " already registered, try another email !"

    override fun register(userDto: UserDto): String {
        val user = userRepository.findByEmail(userDto.email)
        //if user already registered
        if (user != null) {
            return "${userDto.email} $EMAIL_ALREADY_REGISTERED"
        }
        //if registration passwords don't match
        if (!isRegistrationPasswordValid(userDto.password, userDto.matchingPassword)) {
            return PASSWORDS_NOT_MATCH
        }

        userRepository.save(
            User(
                ObjectId.get().toString(),
                firstName = userDto.firstName,
                lastName = userDto.lastName,
                email = userDto.email,
                password = encoder.encode(userDto.password),
                roles = listOf("ROLE_USER")
            )
        )

        return "${userDto.email} $SUCCESS_REGISTRATION_MESSAGE"
    }

    private fun isRegistrationPasswordValid(password: String, matchingPassword: String): Boolean {
        return password == matchingPassword
    }


}
