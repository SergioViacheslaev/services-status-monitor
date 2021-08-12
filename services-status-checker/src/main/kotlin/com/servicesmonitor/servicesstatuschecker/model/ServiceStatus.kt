package com.servicesmonitor.servicesstatuschecker.model

/**
 *  Short service status designation
 */
enum class ServiceStatus {
    /** Initial service status after it has been registered */
    REGISTERED,

    /** Service works correctly */
    UP,

    /** Service is not working, due to errors*/
    DOWN,

    /** Service is not responding for requests */
    OFFLINE
}
