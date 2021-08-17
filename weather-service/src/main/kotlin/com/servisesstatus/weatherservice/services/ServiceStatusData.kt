package com.servisesstatus.weatherservice.services

import com.servisesstatus.weatherservice.dto.ServiceStatusDto


interface ServiceStatusData {
    fun getServiceStatusDto(): ServiceStatusDto
}
