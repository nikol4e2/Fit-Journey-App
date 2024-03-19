package com.example.fitjourneyapp.web.controller;


import com.example.fitjourneyapp.model.User;
import com.example.fitjourneyapp.model.Weight;
import com.example.fitjourneyapp.model.Workout;
import com.example.fitjourneyapp.repository.WorkoutRepository;
import com.example.fitjourneyapp.service.WeightService;
import com.example.fitjourneyapp.service.WorkoutService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.*;

@Controller
@RequestMapping("/profile")
public class ProfileController {

    private final WorkoutService workoutService;
    private final WeightService weightService;

    public ProfileController(WorkoutService workoutService, WeightService weightService) {
        this.workoutService = workoutService;
        this.weightService = weightService;
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

    @GetMapping(path = "/edit")
    public String getEditProfilePage(HttpServletRequest request,Model model)
    {
        User user=(User)request.getSession().getAttribute("user");
        if(user!=null)
        {
            model.addAttribute("user",user);
            model.addAttribute("weight",user.getWeight().get(user.getWeight().size()-1));

        }
        return "editProfile";
    }

    @GetMapping ("/weight-history")
    public String getAddWeightPage(HttpServletRequest request,Model model)
    {
        User user=(User) request.getSession().getAttribute("user");
        if (user!=null)
        {
            List<Weight> weights=user.getWeight();
            Collections.reverse(weights);
            model.addAttribute("weights",weights );
            model.addAttribute("user",user);
        }
        return "weightHistory";
    }


    @PostMapping("/add-weight")
    public String addWeight(HttpServletRequest request,@RequestParam float weight)
    {
        User user=(User) request.getSession().getAttribute("user");
        if (user!=null) {
            Weight weightObj= weightService.save(weight);
            user.getWeight().add(weightObj);

        }
        return "redirect:/profile/weight-history";
    }



}
