package com.example.fitjourneyapp.service.impl;

import com.example.fitjourneyapp.model.User;
import com.example.fitjourneyapp.model.exceptions.InvalidUserCredentialsException;
import com.example.fitjourneyapp.model.exceptions.PasswordsDoNotMatchException;
import com.example.fitjourneyapp.model.exceptions.UserNameAlreadyExistsException;
import com.example.fitjourneyapp.repository.UserRepository;
import com.example.fitjourneyapp.service.AuthService;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;

    public AuthServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User login(String username, String password) {
        if(username==null || username.isEmpty() || password==null || password.isEmpty())
        {
            throw new InvalidUserCredentialsException();
        }
        return userRepository.findByUsernameAndPassword(username,password).orElseThrow(InvalidUserCredentialsException::new);
    }

    @Override
    //Dovrsi so implementiranje
    public User register(String username, String password, String repeatPassword, String name, String surname, Date dateOfBirth, double weight) {
        if(username==null || username.isEmpty() || password==null || password.isEmpty())
        {
            throw new InvalidUserCredentialsException();
        }
        if(!password.equals(repeatPassword))
        {
            throw new PasswordsDoNotMatchException();
        }
        if(this.userRepository.findByUsername(username).isPresent() || !this.userRepository.findByUsername(username).isEmpty())
        {
            throw new UserNameAlreadyExistsException(username);
        }
        User user=new User(username,password,name,surname,dateOfBirth,weight);
        return this.userRepository.save(user);
    }

    @Override
    public User save(User user) {
        return this.userRepository.save(user);
    }
}
