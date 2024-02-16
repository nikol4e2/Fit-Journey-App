package com.example.fitjourneyapp.service;

import com.example.fitjourneyapp.model.DoneExercise;
import com.example.fitjourneyapp.model.User;
import com.example.fitjourneyapp.model.Workout;

import java.util.Optional;

public interface WorkoutService {

    Workout save(String name, User user);
    void addDoneExercise(Long workoutId, DoneExercise exercise);
    void deleteById(Long id);
    void deleteDoneExercise(Long workoutId,Long exerciseId);
    Optional<Workout> findById(Long id);
    double calculateTotalVolume(Long workoutId);
    Workout update(Workout workout);

    //Mozebi ke ima potreba da se implementira vo authservice kaj user-ot
    void addWorkoutToUser(String username,Workout workout);
}
