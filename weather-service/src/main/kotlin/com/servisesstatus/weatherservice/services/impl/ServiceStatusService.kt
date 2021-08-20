package com.servisesstatus.weatherservice.services.impl

import com.servisesstatus.weatherservice.dto.ServiceStatusDto
import com.servisesstatus.weatherservice.model.ServiceStatus
import com.servisesstatus.weatherservice.services.ServiceStatusData
import org.springframework.stereotype.Service

@Service
class ServiceStatusService : ServiceStatusData {
    private var serviceStatus: ServiceStatus = ServiceStatus.OFFLINE

    override fun getServiceStatusDto() = ServiceStatusDto(
        getCurrentStatus(),
        Runtime.getRuntime().totalMemory().toString(),
        Runtime.getRuntime().freeMemory().toString(),
        getServiceExceptions()
    )

    /**
     *  Dummy method to generate random service status
     */
    private fun getCurrentStatus(): ServiceStatus {
        val rnds = (-1..1).random()
        return if (rnds >= 0) {
            serviceStatus = ServiceStatus.UP
            ServiceStatus.UP
        } else {
            serviceStatus = ServiceStatus.DOWN
            ServiceStatus.DOWN
        }
    }


    /**
     *  Dummy method to generate random exceptions messages
     */
    private fun getServiceExceptions(): List<String> {
        val exceptions = mutableListOf<String>()
        if (serviceStatus == ServiceStatus.DOWN) {
            val rnds = (0..3).random()
            when (rnds) {
                1 -> exceptions.add("Error connecting to DB")
                2 -> exceptions.add("Weather API not responding")
                3 -> exceptions.add("Security connection refused")
            }
        }
        return exceptions
    }

}
