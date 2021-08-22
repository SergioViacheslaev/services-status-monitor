package com.servisesstatus.processingservice.services

/**
 * Dummy methods for check status imitation
 * Return empty list if everything is OK or list of exceptions
 */
interface ServiceHealth {
    suspend fun checkDataBaseConnections(): List<String>
    suspend fun checkAmazonCloudAPI(): List<String>
    suspend fun checkMessagingQueue(): List<String>
}
