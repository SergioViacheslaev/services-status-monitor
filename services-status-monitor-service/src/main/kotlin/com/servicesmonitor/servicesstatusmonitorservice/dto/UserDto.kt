package com.servicesmonitor.servicesstatusmonitorservice.dto

import org.jetbrains.annotations.NotNull


data class UserDto(
    @NotNull
    var firstName: String? = null,
    @NotNull
    var lastName: String? = null,
    @NotNull
    var password: String? = null,
    @NotNull
    var matchingPassword: String? = null,
    @NotNull
    var email: String? = null
)

