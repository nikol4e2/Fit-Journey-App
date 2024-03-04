package com.example.fitjourneyapp.web.controller;


import com.example.fitjourneyapp.model.User;
import com.example.fitjourneyapp.model.Workout;
import com.example.fitjourneyapp.repository.WorkoutRepository;
import com.example.fitjourneyapp.service.WorkoutService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/profile")
public class ProfileController {

    private final WorkoutService workoutService;

    public ProfileController(WorkoutService workoutService) {
        this.workoutService = workoutService;
    }

    @GetMapping
    public String getProfilePage(HttpServletRequest request, Model model)
    {
        if(request.getSession().getAttribute("user")==null)
        {
            return "redirect:/home";
        }

        User user=(User) request.getSession().getAttribute("user");
        model.addAttribute("user",user);
        model.addAttribute("workouts",user.getWorkoutsDone());

        return "profile";
    }


}
