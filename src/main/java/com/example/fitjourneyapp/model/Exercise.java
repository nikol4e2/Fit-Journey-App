package com.example.fitjourneyapp.model;

import com.example.fitjourneyapp.model.enumerations.MuscleType;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Exercise {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    //Cuvame informacii za toa koj muskul primarno e pogoden od vezbata
    private MuscleType primaryMuscle;




    public Exercise(String name, MuscleType primaryMuscle) {
        this.name = name;
        this.primaryMuscle = primaryMuscle;

    }

    public Exercise() {
    }
}
