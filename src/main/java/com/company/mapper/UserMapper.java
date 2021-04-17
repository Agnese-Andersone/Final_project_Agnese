package com.company.mapper;

import com.company.dto.UserDTO;
import com.company.model.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    public User fromDTO(UserDTO userDTO) {
        User user = new User();
        user.setId(userDTO.getId());
        user.setUsername(userDTO.getUsername());
        user.setEmail(userDTO.getEmail());
        user.setPersonalCode(userDTO.getPersonalCode());
        user.setAddress(userDTO.getAddress());
        user.setFavouriteBook(userDTO.getFavouriteBook());
        return user;
    }

    public UserDTO toDTO(User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setUsername(user.getUsername());
        userDTO.setEmail(user.getEmail());
        userDTO.setPersonalCode(user.getPersonalCode());
        userDTO.setAddress(user.getAddress());
        userDTO.setFavouriteBook(user.getFavouriteBook());
        return userDTO;
    }
}