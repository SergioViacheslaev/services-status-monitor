package com.servisesstatus.processingservice.confg

import org.springframework.boot.web.client.RestTemplateBuilder
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.client.RestTemplate
import java.time.Duration

@Configuration
class AppConfig {

    @Bean
    fun getRestTemplate(builder: RestTemplateBuilder): RestTemplate {
        return builder
            .setConnectTimeout(Duration.ofSeconds(10))
            .setReadTimeout(Duration.ofSeconds(10)).build()
    }
}
