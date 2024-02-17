package com.example.fitjourneyapp.service.impl;

import com.example.fitjourneyapp.model.Exercise;
import com.example.fitjourneyapp.model.enumerations.MuscleType;
import com.example.fitjourneyapp.repository.ExerciseRepository;
import com.example.fitjourneyapp.service.ExerciseService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ExerciseServiceImpl implements ExerciseService {

    private final ExerciseRepository exerciseRepository;

    public ExerciseServiceImpl(ExerciseRepository exerciseRepository) {
        this.exerciseRepository = exerciseRepository;
    }

    @Override
    public List<Exercise> findAll() {
        return exerciseRepository.findAll();
    }

    @Override
    public List<Exercise> findAllByPrimaryMuscle(MuscleType muscleType) {
        return exerciseRepository.findAllByPrimaryMuscle(muscleType);
    }

    @Override
    public Optional<Exercise> findByName(String name) {
        return this.exerciseRepository.findByName(name);
    }

    @Override
    public Optional<Exercise> findById(Long id) {
        return this.exerciseRepository.findById(id);
    }

    @Override
    public Exercise save(String name, MuscleType muscleType) {
        return this.exerciseRepository.save(new Exercise(name,muscleType));
    }

    @Override
    public void deleteById(Long id) {
        this.exerciseRepository.deleteById(id);
    }
}
