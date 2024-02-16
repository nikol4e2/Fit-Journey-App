package com.example.fitjourneyapp.service;

import com.example.fitjourneyapp.model.DoneExercise;
import com.example.fitjourneyapp.model.ExerciseSet;

import java.util.Optional;

public interface DoneExerciseService {

    Optional<DoneExercise> save(DoneExercise doneExercise);

    void addSet(Long id, ExerciseSet set);
    void deleteById(Long id);
    Optional<DoneExercise> findById(Long id);
    Optional<DoneExercise> update(DoneExercise doneExercise);
}
