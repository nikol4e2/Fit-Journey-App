package com.example.fitjourneyapp.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

//Cuvame informacii za sekoja sraboretena serija od nekoja vezba
@Data
@Entity
public class ExerciseSet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int reps;
    private double weight;


    public ExerciseSet(int reps, double weight) {
        this.reps = reps;
        this.weight = weight;
    }

    public ExerciseSet() {
    }
}
