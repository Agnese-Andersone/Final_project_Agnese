
package com.company.repository;

import com.company.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    List<User> findByStatus(String status);

    User findByStatusAndId(String status, Long id);

    User findByPersonalCodeAndStatus(String personalCode, String status);

    List<User> findByFavouriteBookLikeAndStatus(String favoriteBook, String status);

    List<User> findDistinctByBooksNameLikeAndStatus(String bookName, String status);

}