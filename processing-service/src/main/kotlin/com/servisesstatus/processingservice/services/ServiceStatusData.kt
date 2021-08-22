package com.servisesstatus.processingservice.services

import com.servisesstatus.processingservice.dto.ServiceStatusDto


interface ServiceStatusData {
    fun getServiceStatusDto(): ServiceStatusDto
}
