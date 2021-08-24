package com.servicesmonitor.servicesstatusmonitorservice.service

import com.servicesmonitor.servicesstatusmonitorservice.dto.UserDto


interface UserRegistration {
    fun register(userDto: UserDto): String
}
