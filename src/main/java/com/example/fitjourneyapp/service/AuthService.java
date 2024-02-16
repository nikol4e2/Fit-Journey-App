package com.example.fitjourneyapp.service;

import com.example.fitjourneyapp.model.User;

import java.util.Date;

public interface AuthService {
    User login(String username, String password);
    User register(String username, String passsword, String repeatPassword, String name, String surname, Date dateofBirth,double weight);
    User save(User user);
}
