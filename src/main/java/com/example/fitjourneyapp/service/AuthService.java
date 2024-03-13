package com.example.fitjourneyapp.service;

import com.example.fitjourneyapp.model.User;

import java.util.Date;

public interface AuthService {
    User login(String username, String password);
    User register(String username, String password, String repeatPassword, String name, String surname, Date dateOfBirth,double weight);
    User save(User user);
    User edit(String username, String password, String repeatPassword, String name, String surname, Date dateOfBirth, double weight);
}
