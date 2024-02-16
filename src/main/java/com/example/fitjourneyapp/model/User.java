package com.example.fitjourneyapp.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

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
    private double Weight;

    @OneToMany
    private List<Workout> workousDone;

    public User(String username, String password, String name, String surname, Date dateOfBirth, double weight) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.surname = surname;
        this.dateOfBirth = dateOfBirth;
        Weight = weight;
    }

    public User() {
    }
}
