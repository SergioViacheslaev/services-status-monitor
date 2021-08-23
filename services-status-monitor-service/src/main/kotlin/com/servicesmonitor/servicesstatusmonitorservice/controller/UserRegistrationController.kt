package com.servicesmonitor.servicesstatusmonitorservice.controller

import com.servicesmonitor.servicesstatusmonitorservice.dto.RegistrationResultDto
import com.servicesmonitor.servicesstatusmonitorservice.dto.UserDto
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping

@Controller
@RequestMapping("/services-monitor/user")
class UserRegistrationController {

    @PostMapping("/registration")
    fun registerUserAccount(@ModelAttribute user: UserDto, model: Model): String {
        println(user)
        model.addAttribute("user", UserDto())
        model.addAttribute("registrationResult", RegistrationResultDto("YAHOOO !!!"))
        return "user-registration-page"
    }

    @GetMapping("/registration")
    fun showUserRegistrationPage(model: Model): String {
        val userDto = UserDto()
        model.addAttribute("user", userDto)
        model.addAttribute("registrationResult", RegistrationResultDto(""))
        return "user-registration-page"
    }

}
