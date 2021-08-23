package com.servicesmonitor.servicesstatusmonitorservice.controller

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping

@Controller
@RequestMapping("/services-monitor")
class LoginController {

    @GetMapping("/login")
    fun showMyLoginPage(): String {
        return "login-page"
    }
}
