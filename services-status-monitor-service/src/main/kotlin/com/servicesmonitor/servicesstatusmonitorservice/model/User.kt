package com.servicesmonitor.servicesstatusmonitorservice.model

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

/**
 * User account
 */
@Document("users")
data class User(
    @Id
    val id: String,
    val firstName: String = "",
    val lastName: String = "",
    val email: String = "",
    val password: String = "",
    val roles: List<String> = emptyList()
)



