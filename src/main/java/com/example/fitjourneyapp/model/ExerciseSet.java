package com.example.fitjourneyapp.model;

//Cuvame informacii za sekoja sraboretena serija od nekoja vezba
public class ExerciseSet {

    private Long id;
    private int reps;
    private double weight;


    public ExerciseSet(int reps, double weight) {
        this.reps = reps;
        this.weight = weight;
    }
}
