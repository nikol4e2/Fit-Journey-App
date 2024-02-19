package com.example.fitjourneyapp.repository;

import com.example.fitjourneyapp.model.ExerciseSet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SetRepository extends JpaRepository<ExerciseSet,Long> {
}
