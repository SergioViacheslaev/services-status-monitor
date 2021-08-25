package com.servicesmonitor.servicesstatusmonitorservice.controller

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import springfox.documentation.annotations.ApiIgnore

@Controller
@ApiIgnore
@RequestMapping("/services-monitor")
class LoginController {

    @GetMapping("/login")
    fun showLoginPage(): String {
        return "login-page"
    }
}
