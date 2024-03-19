package com.example.fitjourneyapp.service;

import com.example.fitjourneyapp.model.Weight;

import java.util.Optional;


public interface WeightService {
    Weight save(float value);
    void deleteById(Long id);
    Optional<Weight> findById(Long id);


}
