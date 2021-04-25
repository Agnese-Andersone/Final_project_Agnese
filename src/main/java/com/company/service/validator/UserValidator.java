package com.company.service.validator;

import com.company.exception.*;
import com.company.model.Book;
import com.company.model.User;
import com.company.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class UserValidator {
    private final static int MAX_BOOKS_PER_USER = 5;

    private final UserRepository userRepository;

    @Autowired
    public UserValidator(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void checkIfUserEmailDoesNotExist(String email) {
        User user = new User();
        user.setEmail(email);
        Example<User> userExample = Example.of(user);
        userRepository.findOne(userExample)
                .ifPresent(t -> {
                    throw new EmailAlreadyExistsException
                    ("Such email already exists!: " + t.getEmail());
                });
    }

    public User checkIfUserExists(Long userId) {
        Optional<User> userFromDBOpt =  Optional.ofNullable(userRepository.findByStatusAndId("ACTIVE", userId));
        return userFromDBOpt.orElseThrow(() ->
                new EntityDoesNotExistException
                ("User with the following id: (" + userId + ") does not exist!"));
    }

    public void checkIfUserHasBook(User user, Long bookId) {
        if (!user
                .getBooks()
                .stream()
                .map(Book::getId)
                .collect(Collectors.toSet())
                .contains(bookId)) {
            throw new UserDoesNotHaveThatBookException
            ("User with the following id  (" + user.getId() + ") does not have book with this id (" + bookId + ")");
        }
    }

    public void checkIfUserDoesNotHaveMoreThenFiveBooks(User user) {
        if (user.getBooks().size() >= MAX_BOOKS_PER_USER) {
            throw new UserHasTooManyBooksException
            ("User (" + user.getId() + ") has too many books!");
        }
    }

    public void checkIfUserHasLoyaltyCard(User user, Long loyaltyCardId) {
        if (user.getLoyaltyCard() == null || !user.getLoyaltyCard().getId().equals(loyaltyCardId)) {
            throw new UserDoesNotHaveThatLoyaltyCardException("user("
                    + user.getId() + ") does not have loyaltyCardId (" + loyaltyCardId + ")");
        }
    }

}