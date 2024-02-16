package com.example.fitjourneyapp.service;

import com.example.fitjourneyapp.model.ExerciseSet;

import java.util.Optional;

public interface SetService {
    ExerciseSet save(int reps,Double weight);
    void deleteById(Long id);
    Optional<ExerciseSet> findById(Long id);
}
