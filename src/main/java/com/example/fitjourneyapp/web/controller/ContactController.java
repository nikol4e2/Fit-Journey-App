package com.example.fitjourneyapp.web.controller;

import com.example.fitjourneyapp.model.User;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ContactController {

    @GetMapping(path = "/contact")
    public String getContactPage(HttpServletRequest request, Model model)
    {
        User user=(User) request.getSession().getAttribute("user");
        if(user!=null)
        {
            model.addAttribute("user",user);
        }else
        {
            model.addAttribute("user",null);
        }
        return "contactPage";

    }
}
