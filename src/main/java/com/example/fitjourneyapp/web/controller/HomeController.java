package com.example.fitjourneyapp.web.controller;

import com.example.fitjourneyapp.model.User;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/home")
public class HomeController {

    @GetMapping
    public String getHomePage(HttpServletRequest request, Model model)
    {
        if(request.getSession().getAttribute("user")!=null)
        {
            User user=(User) request.getSession().getAttribute("user");
            model.addAttribute("user",user);
        }
        return "home";
    }
}
