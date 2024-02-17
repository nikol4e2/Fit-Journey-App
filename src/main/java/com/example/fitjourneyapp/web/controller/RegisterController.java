package com.example.fitjourneyapp.web.controller;

import com.example.fitjourneyapp.model.exceptions.InvalidUserCredentialsException;
import com.example.fitjourneyapp.model.exceptions.PasswordsDoNotMatchException;
import com.example.fitjourneyapp.service.AuthService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;

@Controller
@RequestMapping("/register")
public class RegisterController {
    private final AuthService authService;

    public RegisterController(AuthService authService) {
        this.authService = authService;
    }

    @GetMapping
    public String getRegisterPage(@RequestParam(required = false) String error, Model model)
    {
        if(error!=null && !error.isEmpty())
        {
            model.addAttribute("hasError",true);
            model.addAttribute("error",error);
        }
        return "register";
    }

    @PostMapping
    public String register(@RequestParam String username, @RequestParam String password, @RequestParam String repeatPassword, @RequestParam String name, @RequestParam String surname, @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date dateOfBirth, @RequestParam double weight)
    {
        try {
            this.authService.register(username, password, repeatPassword, name, surname, dateOfBirth, weight);
            return "redirect:/home";
        }catch (PasswordsDoNotMatchException | InvalidUserCredentialsException exception)
        {
            return "redirect:/register?error="+exception.getMessage();
        }
    }
}
