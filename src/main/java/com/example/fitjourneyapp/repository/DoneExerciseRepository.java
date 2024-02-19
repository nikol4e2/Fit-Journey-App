package com.example.fitjourneyapp.repository;

import com.example.fitjourneyapp.model.DoneExercise;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DoneExerciseRepository extends JpaRepository<DoneExercise,Long> {
}
