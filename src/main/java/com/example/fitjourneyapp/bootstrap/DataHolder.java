package com.example.fitjourneyapp.bootstrap;

import com.example.fitjourneyapp.model.Exercise;
import com.example.fitjourneyapp.model.User;
import com.example.fitjourneyapp.model.Weight;
import com.example.fitjourneyapp.model.enumerations.MuscleType;
import com.example.fitjourneyapp.repository.ExerciseRepository;
import com.example.fitjourneyapp.repository.UserRepository;
import org.springframework.cglib.core.Local;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Date;

@Component
public class DataHolder {

    private final ExerciseRepository exerciseRepository;
    private final UserRepository userRepository;

    public DataHolder(ExerciseRepository exerciseRepository, UserRepository userRepository) {
        this.exerciseRepository = exerciseRepository;
        this.userRepository = userRepository;
        addExerciseToDB();
    }

    private void addExerciseToDB()
    {


        exerciseRepository.save(new Exercise("Bench press", MuscleType.CHEST));
        exerciseRepository.save(new Exercise("Incline Bench Press", MuscleType.CHEST));
        exerciseRepository.save(new Exercise("Dumbbell Chest flies",MuscleType.CHEST));
        exerciseRepository.save(new Exercise("Push Ups",MuscleType.CHEST));
        exerciseRepository.save(new Exercise("Pull Ups",MuscleType.BACK));
        exerciseRepository.save(new Exercise("Lat Pull Downs",MuscleType.BACK));
        exerciseRepository.save(new Exercise("Pull overs",MuscleType.BACK));
        exerciseRepository.save(new Exercise("Military Press",MuscleType.SHOULDERS));
        exerciseRepository.save(new Exercise("Front Dumbbell Raises",MuscleType.SHOULDERS));
        exerciseRepository.save(new Exercise("Lateral Dumbbell Raises",MuscleType.SHOULDERS));
        exerciseRepository.save(new Exercise("Rear Delt Flies",MuscleType.SHOULDERS));
        exerciseRepository.save(new Exercise("Squat",MuscleType.QUADS));
        exerciseRepository.save(new Exercise("Leg Extensions",MuscleType.QUADS));
        exerciseRepository.save(new Exercise("Lunges",MuscleType.QUADS));
        exerciseRepository.save(new Exercise("Bulgarian Split Squat",MuscleType.QUADS));
        exerciseRepository.save(new Exercise("Shrugs",MuscleType.TRAPS));
        exerciseRepository.save(new Exercise("Deadlift",MuscleType.HAMSTRINGS));
        exerciseRepository.save(new Exercise("Stiff Leg Deadlift",MuscleType.HAMSTRINGS));
        exerciseRepository.save(new Exercise("Calf Raises",MuscleType.CALVES));
        exerciseRepository.save(new Exercise("Triceps Extensions",MuscleType.TRICEPS));
        exerciseRepository.save(new Exercise("Skull Crushers",MuscleType.TRICEPS));

    }
}
