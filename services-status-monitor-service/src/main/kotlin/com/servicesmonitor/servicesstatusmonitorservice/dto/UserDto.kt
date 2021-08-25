package com.servicesmonitor.servicesstatusmonitorservice.dto

/**
 * User registration data
 */
data class UserDto(
    val firstName: String = "",
    val lastName: String = "",
    val password: String = "",
    val matchingPassword: String = "",
    val email: String = ""
)

