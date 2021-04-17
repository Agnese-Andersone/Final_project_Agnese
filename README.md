# final_project

1st homework
User
* id
* username
* email
* personalCode
* address
* favoriteBook


1. Create .SQL -> db.migration (create table) - done
2. Create Entity User -> model - done
3. Create UserDTO -> dto  - done
4. Create UserMapper - done
5. UserRepository with findByPersonalCode method - done
6. UserService -> (using userRepository) - done
7. UserController (getAll) (addNewUser) (findByPersonalCode)  (using UserService) - done.


- localhost:9000/h2-console
- localhost:9000/swagger-ui.html

2nd homework
1. Methods
a) AddMethods to assign book to user
b) Remove book from user

(Long bookId, Long user)
find book (id)
find user (id)
book.setUser(user) => save

2. Validation
Before assign book to user check that user does not have more then 5 book.
If do Throw UserHaveTooManyBookException (create new exception)
