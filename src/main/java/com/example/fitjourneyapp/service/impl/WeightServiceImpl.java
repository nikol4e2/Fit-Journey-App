package com.example.fitjourneyapp.service.impl;

import com.example.fitjourneyapp.model.Weight;
import com.example.fitjourneyapp.repository.WeightRepository;
import com.example.fitjourneyapp.service.WeightService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class WeightServiceImpl implements WeightService {

    private final WeightRepository weightRepository;


    public WeightServiceImpl(WeightRepository weightRepository) {
        this.weightRepository = weightRepository;
    }

    @Override
    public Weight save(float value) {
        return this.weightRepository.save(new Weight(value));
    }

    @Override
    public void deleteById(Long id) {
        this.weightRepository.deleteById(id);
    }

    @Override
    public Optional<Weight> findById(Long id) {
        return this.weightRepository.findById(id);
    }
}
