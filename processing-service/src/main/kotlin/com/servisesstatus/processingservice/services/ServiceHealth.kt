package com.servisesstatus.processingservice.services


interface ServiceHealth {
    suspend fun checkDataBaseConnections(): List<String>
    suspend fun checkAmazonCloudAPI(): List<String>
    suspend fun checkMessagingQueue(): List<String>
}
