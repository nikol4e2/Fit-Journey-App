package com.example.fitjourneyapp.web.controller;

import com.example.fitjourneyapp.model.*;
import com.example.fitjourneyapp.service.*;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class WorkoutController {

    private final WorkoutService workoutService;
    private final ExerciseService exerciseService;
    private final AuthService authService;
    private final DoneExerciseService doneExerciseService;
    private final SetService setService;


    public WorkoutController(WorkoutService workoutService, ExerciseService exerciseService, AuthService authService, DoneExerciseService doneExerciseService, SetService setService) {
        this.workoutService = workoutService;
        this.exerciseService = exerciseService;
        this.authService = authService;
        this.doneExerciseService = doneExerciseService;
        this.setService = setService;
    }


    @GetMapping(path = "/add-workout")
    public String getWorkoutPage(HttpServletRequest request, Model model)
    {
        User user=(User) request.getSession().getAttribute("user");
        model.addAttribute("workouts",user.getWorkoutsDone());
        return "addWorkout";
    }

    @PostMapping(path = "/add-workout")
    public String addWorkout(HttpServletRequest request, @RequestParam String name)
    {
        User user= (User) request.getSession().getAttribute("user");

        //Zaradi ovaa linija na kod mozebi da se zacucuva dva pati workout
        Workout workout=this.workoutService.save(name,user);


        user.getWorkoutsDone().add(workout);
        authService.save(user);
        return "redirect:/workout/"+workout.getWorkoutId()+"/add-exercise";
    }
    @GetMapping(path = "workout/{id}/add-exercise")
    public String getAddExercisePage(HttpServletRequest request, Model model, @PathVariable Long id)
    {
        //Gi dodadavame site vezbi koi se dodadeni vo bazata na podatoci za da moze korisnikot da izbere od niv
        model.addAttribute("exercises",exerciseService.findAll());
        if(this.workoutService.findById(id).isPresent())
        {
            Workout workout=workoutService.findById(id).get();
            model.addAttribute("workout",workout);

            //Gi predavame site gotovi vezbi koi veke se dodadeni na treningot
            model.addAttribute("doneExercises",workout.getExercises());
            return  "addExerciseToWorkout";
        }
        return "redirect:/add-workout";
    }

    @PostMapping(path = "/workout/add-exercise")
    public String addDoneExercise(@RequestParam Long exerciseId,@RequestParam Long workoutId)
    {
        if(this.workoutService.findById(workoutId).isPresent())
        {
            Workout workout=workoutService.findById(workoutId).get();
            Exercise exercise=exerciseService.findById(exerciseId).get();
            DoneExercise doneExercise=new DoneExercise(exercise);
            doneExerciseService.save(doneExercise);
            workoutService.addDoneExercise(workoutId,doneExercise);
            return "redirect:/workout/"+workoutId+"/add-exercise";
        }
        return "redirect:/workout/"+workoutId+"/add-exercise";

    }



    //Dodavanje na set vo vezba

    @PostMapping(path = "/add-set")
    public String addSet(@RequestParam Long workoutId,@RequestParam Long doneExerciseId,@RequestParam int reps,@RequestParam double weight)
    {
        if(this.doneExerciseService.findById(doneExerciseId).isPresent())
        {
            DoneExercise doneExercise=this.doneExerciseService.findById(doneExerciseId).get();
            doneExercise.getSets().add(new ExerciseSet(reps,weight));
            this.doneExerciseService.update(doneExercise);
        }
        return "redirect:/workout/"+workoutId+"/add-exercise";

    }

    //Go zavrsuvame treningot so sto presmetuvame totalVolume i preminuvame na finalniot del, odnosno komentar za treningot
    @PostMapping(path = "/workout/complete-workout")
    public String completeWorkout(@RequestParam Long workoutId,HttpServletRequest request,Model model)
    {
        if(this.workoutService.findById(workoutId).isPresent())
        {
            Workout workout=this.workoutService.findById(workoutId).get();
            model.addAttribute("totalVolume",workoutService.calculateTotalVolume(workoutId));
            request.getSession().setAttribute("totalVolume",workout.getTotalVolume());
            request.getSession().setAttribute("workout",workout);
        }
        return "redirect:/workout/"+workoutId+"/finalize";
    }

    @GetMapping("/workout/{id}/finalize")
    public String finalizeWorkout(@PathVariable Long id,HttpServletRequest request,Model model)
    {
        model.addAttribute("workout",(Workout)request.getSession().getAttribute("workout"));
        model.addAttribute("totalVolume",request.getSession().getAttribute("totalVolume"));
        return "finalizeWorkout";
    }

    @PostMapping(path = "/workout/finalize")
    public String addCommnet(HttpServletRequest request,@RequestParam Long workoutId,@RequestParam String comment)
    {
        User user=(User) request.getSession().getAttribute("user");
        if(this.workoutService.findById(workoutId).isPresent() && user!=null)
        {
            Workout workout=this.workoutService.findById(workoutId).get();
            workout.setComment(comment);
            workoutService.update(workout);
            //user.getWorkousDone().add(workout); MOZEBI GO DODAVAM DVAPATI ISTIO WORKOUT OVDE
            //this.authService.save(user);
        }
        return "redirect:/profile";
    }

    @PostMapping(path = "/workout/delete/{id}")
    public String deleteWorkout(@PathVariable Long id,HttpServletRequest request)
    {
        if(this.workoutService.findById(id).isPresent())
        {
            User user=(User) request.getSession().getAttribute("user");
            user.getWorkoutsDone().removeIf(r->r.getWorkoutId().equals(id));
            this.authService.save(user);
            this.workoutService.deleteById(id);


        }
        return "redirect:/profile";

    }
}
