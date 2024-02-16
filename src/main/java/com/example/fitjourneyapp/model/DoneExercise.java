package com.example.fitjourneyapp.model;


import java.util.ArrayList;
import java.util.List;

//Ovoj objekt ke gi sodrzi site sraboteni serii za odredena vezba
public class DoneExercise {

    private Long id;

    private Exercise exercise;

    List<ExerciseSet> sets;

    public DoneExercise(Exercise exercise) {
        this.exercise = exercise;
        this.sets=new ArrayList<>();
    }
}
