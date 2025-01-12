package com.servicesmonitor.servicesstatusmonitorservice.repo

import com.servicesmonitor.servicesstatusmonitorservice.model.MonitoredService
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository

interface MonitoredServiceRepository : MongoRepository<MonitoredService, String> {
    fun findByServiceName(serviceName: String): MonitoredService?
}
