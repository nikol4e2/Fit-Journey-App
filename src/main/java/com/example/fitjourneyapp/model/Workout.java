package com.example.fitjourneyapp.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class Workout {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="workoutId", unique = true, nullable = false)
    private Long workoutId;
    private String name;
    private LocalDate workoutDate;

    @OneToMany(cascade= CascadeType.ALL,fetch = FetchType.EAGER)
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

    public Workout() {
    }
}
