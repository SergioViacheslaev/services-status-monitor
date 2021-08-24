package com.servicesmonitor.servicesstatusmonitorservice.repo

import com.servicesmonitor.servicesstatusmonitorservice.model.User
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository

@Repository
interface UserRepository : MongoRepository<User, String> {
    fun findByEmail(email: String): User?
}
