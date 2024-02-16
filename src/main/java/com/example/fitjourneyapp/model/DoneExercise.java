package com.example.fitjourneyapp.model;


import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

//Ovoj objekt ke gi sodrzi site sraboteni serii za odredena vezba

@Entity
@Data
public class DoneExercise {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Exercise exercise;

    @OneToMany
    List<ExerciseSet> sets;

    public DoneExercise(Exercise exercise) {
        this.exercise = exercise;
        this.sets=new ArrayList<>();
    }

    public DoneExercise() {
    }
}
