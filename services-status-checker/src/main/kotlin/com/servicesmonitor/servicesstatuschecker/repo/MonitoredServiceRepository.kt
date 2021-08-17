package com.servicesmonitor.servicesstatuschecker.repo

import com.servicesmonitor.servicesstatuschecker.model.MonitoredService
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository

@Repository
interface MonitoredServiceRepository : MongoRepository<MonitoredService, String>
