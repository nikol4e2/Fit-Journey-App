package com.example.fitjourneyapp.repository;

import com.example.fitjourneyapp.model.Weight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface WeightRepository extends JpaRepository<Weight,Long> {

}
