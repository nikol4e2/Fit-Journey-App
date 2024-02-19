package com.example.fitjourneyapp.web.controller;

import com.example.fitjourneyapp.service.AuthService;
import com.example.fitjourneyapp.service.ExerciseService;
import com.example.fitjourneyapp.service.WorkoutService;
import org.springframework.stereotype.Controller;

@Controller
public class WorkoutController {

    private final WorkoutService workoutService;
    private final ExerciseService exerciseService;
    private final AuthService authService;
}
