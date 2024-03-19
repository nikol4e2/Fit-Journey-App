package com.example.fitjourneyapp.model;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@Entity(name = "app-user")
public class User {
    @Id
    private String username;
    private String password;
    private String name;
    private String surname;
    @DateTimeFormat(pattern = "yyyyy-MM-dd")
    private Date dateOfBirth;
    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_weight")
    private List<Weight> weight;

    @JoinColumn(name = "user_workouts", referencedColumnName="username")
    @OneToMany(fetch = FetchType.EAGER,cascade=CascadeType.REMOVE,orphanRemoval = true)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private List<Workout> workoutsDone;

    public User(String username, String password, String name, String surname, Date dateOfBirth, Weight w) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.surname = surname;
        this.dateOfBirth = dateOfBirth;
        this.weight=new ArrayList<>();
        this.weight.add(w);
    }

    public User() {
    }

}
