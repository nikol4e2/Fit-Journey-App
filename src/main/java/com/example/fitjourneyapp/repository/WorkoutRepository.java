package com.example.fitjourneyapp.repository;

import com.example.fitjourneyapp.model.Workout;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface WorkoutRepository extends JpaRepository<Workout,Long> {

    Optional<Workout> findByName(String name);
}
