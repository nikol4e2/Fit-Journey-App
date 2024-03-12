package com.example.fitjourneyapp.service.impl;

import com.example.fitjourneyapp.model.ExerciseSet;
import com.example.fitjourneyapp.repository.SetRepository;
import com.example.fitjourneyapp.service.SetService;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class SetServiceImpl implements SetService {

    private final SetRepository setRepository;

    public SetServiceImpl(SetRepository setRepository) {
        this.setRepository = setRepository;
    }

    @Override
    public ExerciseSet save(int reps, Double weight) {
        return this.setRepository.save(new ExerciseSet(reps,weight));
    }

    @Override
    public void deleteById(Long id) {
        this.setRepository.deleteById(id);
    }

    @Override
    public Optional<ExerciseSet> findById(Long id) {
        return this.setRepository.findById(id);
    }

    @Override
    public Optional<ExerciseSet> edit(Long id, int reps, Double weight) {
        ExerciseSet exerciseSet=this.findById(id).get();
        if(exerciseSet!=null)
        {
            exerciseSet.setReps(reps);
            exerciseSet.setWeight(weight);
            this.setRepository.save(exerciseSet);
        }
        return Optional.of(exerciseSet);
    }


}
