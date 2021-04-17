package com.company.service;

import com.company.model.User;
import com.company.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
    public List<User> getAllUsersByPersonalCode(String personalCode) {
        return userRepository.findByPersonalCode(personalCode);
    }

    public User saveUser(User user) {
        return userRepository.save(user);
    }
    public User updateUser(User user) {
        User userFromDb = userRepository.getOne(user.getId());
        if(userFromDb != null){
            return userRepository.save(user);
        }else {
            throw new RuntimeException("User with id: " + user.getId()
                    + "does not exist!");
        }
    }

}