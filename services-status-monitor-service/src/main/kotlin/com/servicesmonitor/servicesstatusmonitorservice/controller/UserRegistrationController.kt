package com.servicesmonitor.servicesstatusmonitorservice.controller

import com.servicesmonitor.servicesstatusmonitorservice.dto.RegistrationResultDto
import com.servicesmonitor.servicesstatusmonitorservice.dto.UserDto
import com.servicesmonitor.servicesstatusmonitorservice.service.UserRegistration
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import springfox.documentation.annotations.ApiIgnore

@Controller
@ApiIgnore
@RequestMapping("/services-monitor/user")
class UserRegistrationController(
    val userRegistrationService: UserRegistration
) {

    @PostMapping("/registration")
    fun registerUserAccount(@ModelAttribute user: UserDto, model: Model): String {
        println(user)
        val registrationResultMessage = userRegistrationService.register(user)
        model.addAttribute("user", user)
        model.addAttribute("registrationResult", RegistrationResultDto(registrationResultMessage))
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
