package com.servisesstatus.processingservice.model

/**
 *  Short service status designation
 */
enum class ServiceStatus {
    /** Service works correctly */
    UP,

    /** Service is not working, due to errors*/
    DOWN,

    /** Service is not responding for requests */
    OFFLINE
}
