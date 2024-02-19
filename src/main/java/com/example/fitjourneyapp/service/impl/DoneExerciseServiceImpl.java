package com.example.fitjourneyapp.service.impl;

import com.example.fitjourneyapp.model.DoneExercise;
import com.example.fitjourneyapp.model.ExerciseSet;
import com.example.fitjourneyapp.service.DoneExerciseService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DoneExerciseServiceImpl implements DoneExerciseService {

    private final DoneExerciseService doneExerciseService;

    public DoneExerciseServiceImpl(DoneExerciseService doneExerciseService) {
        this.doneExerciseService = doneExerciseService;
    }

    @Override
    public Optional<DoneExercise> save(DoneExercise doneExercise) {
        return Optional.empty();
    }

    @Override
    public void addSet(Long id, ExerciseSet set) {

    }

    @Override
    public void deleteById(Long id) {

    }

    @Override
    public Optional<DoneExercise> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public Optional<DoneExercise> update(DoneExercise doneExercise) {
        return Optional.empty();
    }
}
