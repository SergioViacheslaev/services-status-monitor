package com.servicesmonitor.servicesstatusmonitorservice.service.websocket

import org.springframework.messaging.simp.SimpMessagingTemplate
import org.springframework.stereotype.Service

@Service
class WebSocketMessageService(
    val messageSender: SimpMessagingTemplate
) : WebSocketMessage {

    override fun sendMessage(topicName: String, message: String) {
        messageSender.convertAndSend(topicName, message)
    }
}
