package com.company.service;

import com.company.model.User;
import com.company.repository.UserRepository;
import com.company.service.validator.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserService {

    private final UserRepository userRepository;
    private final UserValidator userValidator;

    @Autowired
    public UserService(UserRepository userRepository,
                       UserValidator userValidator) {

        this.userRepository = userRepository;
        this.userValidator = userValidator;
    }

    public List<User> findAllUsers() {
        return userRepository.findByStatus("ACTIVE");
    }

    public User getByPersonalCode(String personalCode) {
        return userRepository.findByPersonalCodeAndStatus("ACTIVE", personalCode);
    }

   public List<User> getByFavouriteBook(String favouriteBook){
        return userRepository.findByFavouriteBookLikeAndStatus("ACTIVE", "%" + favouriteBook + "%");
   }

   public User saveUser(User user){
        return userRepository.save(user);
   }

   public User getUserById(Long id){
        return userRepository.getOne(id);
   }
    public User updateUser(User user) {
        userValidator.checkIfUserExists(user.getId());
        return userRepository.save(user);
    }
    public List<User> getUsersByBookName(String bookName){
        return userRepository.findDistinctByBooksNameLikeAndStatus("ACTIVE","%" + bookName + "%");
    }

    public void softDeleteUser(Long userId){
        User user = userValidator.checkIfUserExists(userId);
        user.setStatus("DELETED");
        userRepository.save(user);
    }
}