package com.example.fitjourneyapp.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Entity
@Data
public class Weight {
    @Id
    @GeneratedValue
    private Long id;


    private float weightValue;
    @DateTimeFormat(pattern = "yyyyy-MM-dd")
    private LocalDate date;

    public Weight(float weightValue) {
        this.weightValue = weightValue;
        this.date= LocalDate.now();
    }

    public Weight() {
    }
}
