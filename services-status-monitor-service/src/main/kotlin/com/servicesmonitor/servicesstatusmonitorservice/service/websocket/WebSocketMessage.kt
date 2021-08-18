package com.servicesmonitor.servicesstatusmonitorservice.service.websocket


interface WebSocketMessage {
    fun sendMessage(topicName: String, message: String)
}
