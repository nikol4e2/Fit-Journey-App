package com.example.fitjourneyapp.web.controller;

import com.example.fitjourneyapp.model.User;
import com.example.fitjourneyapp.model.exceptions.InvalidUserCredentialsException;
import com.example.fitjourneyapp.service.AuthService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/login")
public class LoginController {
    private final AuthService authService;

    public LoginController(AuthService authService) {
        this.authService = authService;
    }

    @GetMapping
    public String getLoginPage()
    {
        return "login";
    }

    @PostMapping
    public String login(HttpServletRequest request, Model model)
    {
        User user=null;

        try {
            user = authService.login(request.getParameter("username"), request.getParameter("password"));
            request.getSession().setAttribute("user", user);
            model.addAttribute("user", user);
            return "redirect:/home";
        }catch (InvalidUserCredentialsException exception){
            model.addAttribute("hasError",true);
            model.addAttribute("error",exception.getMessage());
            return "login";
        }
    }
}
