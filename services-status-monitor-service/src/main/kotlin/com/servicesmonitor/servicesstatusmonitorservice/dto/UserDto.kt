package com.servicesmonitor.servicesstatusmonitorservice.dto


data class UserDto(
    val firstName: String = "",
    val lastName: String = "",
    val password: String = "",
    val matchingPassword: String = "",
    val email: String = ""
)

