package com.example.fitjourneyapp.service.impl;

import com.example.fitjourneyapp.model.DoneExercise;
import com.example.fitjourneyapp.model.ExerciseSet;
import com.example.fitjourneyapp.model.exceptions.ExerciseDoesNotExistException;
import com.example.fitjourneyapp.repository.DoneExerciseRepository;
import com.example.fitjourneyapp.service.DoneExerciseService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DoneExerciseServiceImpl implements DoneExerciseService {

    private final DoneExerciseRepository doneExerciseRepository;

    public DoneExerciseServiceImpl(DoneExerciseRepository doneExerciseService) {
        this.doneExerciseRepository = doneExerciseService;
    }

    @Override
    public Optional<DoneExercise> save(DoneExercise doneExercise) {

        return Optional.of(doneExerciseRepository.save(doneExercise));
    }

    @Override
    public void addSet(Long id, ExerciseSet set) {
        if(this.doneExerciseRepository.findById(id).isPresent())
        {
            DoneExercise doneExercise=doneExerciseRepository.findById(id).get();
            doneExercise.getSets().add(set);

        }else throw new ExerciseDoesNotExistException(id);
    }

    @Override
    public void deleteById(Long id) {
        this.doneExerciseRepository.deleteById(id);

    }

    @Override
    public Optional<DoneExercise> findById(Long id) {
        return this.doneExerciseRepository.findById(id);
    }

    @Override
    public Optional<DoneExercise> update(DoneExercise doneExercise) {
        return Optional.of(this.doneExerciseRepository.save(doneExercise));
    }
}
