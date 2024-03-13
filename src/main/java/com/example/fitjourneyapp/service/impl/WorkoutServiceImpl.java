package com.example.fitjourneyapp.service.impl;

import com.example.fitjourneyapp.model.DoneExercise;
import com.example.fitjourneyapp.model.Exercise;
import com.example.fitjourneyapp.model.User;
import com.example.fitjourneyapp.model.Workout;
import com.example.fitjourneyapp.repository.UserRepository;
import com.example.fitjourneyapp.repository.WorkoutRepository;
import com.example.fitjourneyapp.service.DoneExerciseService;
import com.example.fitjourneyapp.service.WorkoutService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class WorkoutServiceImpl implements WorkoutService {

    private final WorkoutRepository workoutRepository;
    private final DoneExerciseService doneExerciseService;
    private final UserRepository userRepository;

    public WorkoutServiceImpl(WorkoutRepository workoutRepository, DoneExerciseService doneExerciseService,UserRepository userRepository) {
        this.workoutRepository = workoutRepository;
        this.doneExerciseService = doneExerciseService;
        this.userRepository=userRepository;
    }

    @Override
    public Workout save(String name, User user) {
        return this.workoutRepository.save(new Workout(name, LocalDate.now()));
    }

    @Override
    public void addDoneExercise(Long workoutId, DoneExercise exercise) {

        if(this.workoutRepository.findById(workoutId).isPresent()) {
            Workout workout = this.workoutRepository.findById(workoutId).get();
            workout.getExercises().add(exercise);
            this.workoutRepository.save(workout);

        }
    }

    @Override
    public void deleteById(Long id) {
        this.workoutRepository.deleteById(id);
    }

    @Override
    public void deleteDoneExercise(Long workoutId, Long exerciseId) {
        //TODO -Implement

    }

    @Override
    public Optional<Workout> findById(Long id) {
        return this.workoutRepository.findById(id);
    }

    @Override
    public double calculateTotalVolume(Long workoutId) {
        Workout workout=this.workoutRepository.findById(workoutId).get();
        double volume=0;

        List<DoneExercise> exercises= workout.getExercises();
        for(int i=0;i<exercises.size();i++)
        {
            for(int j=0;j<exercises.get(i).getSets().size();j++)
            {
                DoneExercise exercise=exercises.get(i);
                volume+=exercise.getSets().get(j).getWeight()*exercise.getSets().get(j).getReps();
            }
        }
        workout.setTotalVolume(volume);
        this.workoutRepository.save(workout);
        return volume;
    }

    @Override
    public Workout update(Workout workout) {
        return this.workoutRepository.save(workout);
    }

    @Override
    public void addWorkoutToUser(String username, Workout workout) {
        if(userRepository.findByUsername(username).isPresent())
        {
            User user=userRepository.findByUsername(username).get();
            user.getWorkoutsDone().add(workout);
            userRepository.save(user);
        }
    }


}
