package com.example.fitjourneyapp.repository;

import com.example.fitjourneyapp.model.Exercise;
import com.example.fitjourneyapp.model.enumerations.MuscleType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ExerciseRepository extends JpaRepository<Exercise,Long> {

    Optional<Exercise> findByName(String name);
    void deleteByName(String name);
    List<Exercise> findAllByPrimaryMuscle(MuscleType muscleType);
}
