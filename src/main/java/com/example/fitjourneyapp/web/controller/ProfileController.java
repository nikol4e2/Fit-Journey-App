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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

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
        List<Workout> workouts=user.getWorkoutsDone();
        if(!workouts.isEmpty()) {
            model.addAttribute("workouts", user.getWorkoutsDone());
            //Koristime hasmapa za da gi mapirame site treninzi spored imeto
            HashMap<String,List<Workout>> workoutsByName=new HashMap<>();
            for(int i=0;i<workouts.size();i++)
            {
                Workout workout=workouts.get(i);
                if(!workoutsByName.containsKey(workout.getName()))
                {
                    //Dokolku go nemame imeto kako kluc dodavame nov par i inicijalizrame lista od workouts
                    workoutsByName.put(workout.getName(),new ArrayList<>());
                }
                //Ovde samo go dodavame workout vo listata spored imeto na workoutot
                workoutsByName.get(workout.getName()).add(workout);
            }
            request.getSession().setAttribute("workoutsByName",workoutsByName);
            model.addAttribute("workoutsByName",workoutsByName);
        }

        return "profile";
    }


}
