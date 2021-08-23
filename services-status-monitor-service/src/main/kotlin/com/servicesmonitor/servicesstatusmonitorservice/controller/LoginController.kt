package com.servicesmonitor.servicesstatusmonitorservice.controller

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping

@Controller
@RequestMapping("/services-monitor/user")
class LoginController {

    @GetMapping("/login")
    fun showLoginPage(): String {
        return "login-page"
    }
}
