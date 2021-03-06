package com.servisesstatus.weatherservice.controller

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import springfox.documentation.annotations.ApiIgnore


@Controller
@ApiIgnore
@RequestMapping("/weather-service")
class WeatherInfoController {

    @GetMapping("/weather-info")
    fun showWeatherInfo(): String {
        return "weather-info"
    }

}
