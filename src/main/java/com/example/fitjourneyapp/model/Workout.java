package com.example.fitjourneyapp.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Workout {

    private Long workoutId;
    private String name;
    private LocalDate workoutDate;

    private List<DoneExercise> exercises;
    private double totalVolume;
    private String comment;

    public Workout(String name, LocalDate workoutDate) {
        this.name = name;
        this.workoutDate = workoutDate;
        this.totalVolume=0;
        this.comment="";
        this.exercises=new ArrayList<>();
    }
}
