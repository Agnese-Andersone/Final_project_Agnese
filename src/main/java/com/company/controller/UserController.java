package com.company.controller;

import com.company.dto.UserDTO;
import com.company.mapper.UserMapper;
import com.company.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/rest/User.svc")
public class UserController {

    private final UserMapper userMapper;
    private final UserService userService;

    @Autowired
    public UserController(UserMapper userMapper,
                          UserService userService) {
        this.userMapper = userMapper;
        this.userService = userService;
    }

    @GetMapping("/users")
    public List<UserDTO> findAllUsers() {
        return userService.getAllUsers().stream()
                .map(userMapper::toDTO)
                .collect(Collectors.toList());

    }

    @GetMapping("/User/personalCode/{personalCode}")
    public List<UserDTO> findAllUsers(@PathVariable("personalCode")
                                              String personalCode) {
        return userService.getAllUsersByPersonalCode(personalCode)
                .stream()
                .map(userMapper::toDTO)
                .collect(Collectors.toList());
    }


    @PostMapping("/User")
    public UserDTO createUser(@RequestBody UserDTO user) {
        return userMapper.toDTO(
                userService.saveUser(userMapper.fromDTO(user)));
    }

    @PutMapping("/User")
    public void updateUser(@RequestBody UserDTO user) {
        userService.updateUser(userMapper.fromDTO(user));
    }
}