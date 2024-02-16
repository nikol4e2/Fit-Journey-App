package com.example.fitjourneyapp.service;

import com.example.fitjourneyapp.model.Exercise;
import com.example.fitjourneyapp.model.enumerations.MuscleType;

import java.util.List;
import java.util.Optional;

public interface ExerciseService {

    List<Exercise> findAll();
    //Potrebno za implementiranje na prebaruvanje spored muskul
    List<Exercise> findAllByPrimaryMuscle(MuscleType muscleType);
    Optional<Exercise> findByName(String name);
    Optional<Exercise> findById(Long id);
    Exercise save(String name, MuscleType muscleType);
    void deleteById(Long id);
}
