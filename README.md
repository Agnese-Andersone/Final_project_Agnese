# final_project

1st homework
User
* id
* username
* email
* personalCode
* address
* favoriteBook


1. Create .SQL -> db.migration (create table)
2. Create Entity User -> model 
3. Create UserDTO -> dto  
4. Create UserMapper - done
5. UserRepository with findByPersonalCode method 
6. UserService -> (using userRepository) 
7. UserController (getAll) (addNewUser) (findByPersonalCode)  (using UserService) 


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

3rd homework
A) Organisation.
  -> Intellij IDEA Ultimate (ask for key or trial 30 days, without ultimate React/Angular will be hard)
  -> Define the topic(Theme) of final project and group (1-4 people) make approx plan what should be done
  -> (optional) Register Facebook developer console or Google Console (OAuth),
      create test GMAIL (not connected with you acc)

B) Bussines request:
User should have LoyalityCard
* Loyality card could be one of 4 types: Bronze, Silver, Gold, Platinum
* Loyality card contains number, and information about user. User can have only one loyality card

- if user have loyality card he can take more books
Bronze: 7
Silver: 10
Gold: 15
Platinum: 20

Should be methods, to assign loyality card to user, remove loyality card from user, change loyality card type.

java -jar -Dspring.profiles.active=[ PROFILE ] [ JAR NAME ].jar
java -jar -Dspring.profiles.active=dev final-project-j13-1.0-SNAPSHOT.jar
