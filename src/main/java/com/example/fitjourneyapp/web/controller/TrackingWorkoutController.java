package com.example.fitjourneyapp.web.controller;

import com.example.fitjourneyapp.model.*;
import com.example.fitjourneyapp.service.*;
import jakarta.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


@Controller
public class TrackingWorkoutController {

    private final WorkoutService workoutService;
    private final ExerciseService exerciseService;
    private final AuthService authService;
    private final DoneExerciseService doneExerciseService;
    private final SetService setService;

    public TrackingWorkoutController(WorkoutService workoutService, ExerciseService exerciseService, AuthService authService, DoneExerciseService doneExerciseService, SetService setService) {
        this.workoutService = workoutService;
        this.exerciseService = exerciseService;
        this.authService = authService;
        this.doneExerciseService = doneExerciseService;
        this.setService = setService;
    }

    @GetMapping(path = "/edit-existing/{id}")
    public String getEditWorkout(@PathVariable Long id, HttpServletRequest request,Model model)
    {
        Workout workout=(Workout) workoutService.findById(id).get();
        if(workout!=null)
        {
            model.addAttribute("newWorkout",workout);
            model.addAttribute("previoslyDoneExercises",workout.getExercises());
            model.addAttribute("exercises",exerciseService.findAll());
            return "trackworkout";
        }
        model.addAttribute("hasError",true);

        return "trackworkout";
    }

    @PostMapping(path = "/add-existing-workout")
    public String track(@RequestParam Long workoutId)
    {
        return "redirect:/add-existing-workout/"+workoutId;
    }

    //Imam 2 mapping za get pri tracking na wokrkout, ovoj e dokolku prv pat go kreirame noviot workout so template od stariot
    @GetMapping(path = "/add-existing-workout/{id}")
    public String getTrackWorkout(@PathVariable Long id,  HttpServletRequest request, Model model) {
        Workout oldWorkout=workoutService.findById(id).get();
        if(oldWorkout!=null)
        {
            model.addAttribute("oldWorkout",oldWorkout);

            User user=(User)request.getSession().getAttribute("user");
            Workout newWorkout=workoutService.save(oldWorkout.getName(),user);
            //Gi dodavame site vezbi so sets od stariot vo noviot workout
            List<DoneExercise> newExercises=new ArrayList<>();
            for(int i=0;i<oldWorkout.getExercises().size();i++)
            {
                DoneExercise oldExercise=oldWorkout.getExercises().get(i);
                DoneExercise exercise=new DoneExercise(oldExercise.getExercise());
                for(int j=0;j<oldExercise.getSets().size();j++)
                {


                    ExerciseSet set=setService.save(oldExercise.getSets().get(j).getReps(),oldExercise.getSets().get(j).getWeight());
                    exercise.getSets().add(set);

                }

                newExercises.add(exercise);
                doneExerciseService.save(exercise);
            }
            newWorkout.setExercises(newExercises);
            workoutService.update(newWorkout);
            user.getWorkoutsDone().add(newWorkout);
            authService.save(user);

            return "redirect:/edit-existing/"+newWorkout.getWorkoutId();
        }
        model.addAttribute("hasError",true);
        model.addAttribute("error","Old workout not found");
        return "trackworkout";
    }


    //Dokolku ima izmeni vo sets i reps za noviot workout ovde gi zacuvuvame tie izmeni
    @PostMapping(path="/edit/set")
    private String editSet(@RequestParam Long workoutId,@RequestParam Long id,@RequestParam int reps,@RequestParam double weight)
    {
        if(id!=null)
        {
            this.setService.edit(id,reps,weight);
        }
        return "redirect:/edit-existing/"+workoutId;
    }
    @PostMapping("/workout/delete-exercise")
    public String deleteExercise(@RequestParam Long workoutId,@RequestParam Long exerciseId)
    {
        this.doneExerciseService.deleteById(exerciseId);
        return "redirect:/edit-existing/"+workoutId;
    }

}
