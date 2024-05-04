package swe.group2.springbootjacocogradle.controller;

import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import swe.group2.springbootjacocogradle.model.Users;
import swe.group2.springbootjacocogradle.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping
public class UserController {

    private UserRepository userRepository;

    @Autowired
    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // get all users
    @GetMapping("/users")
    public ResponseEntity<List<Users>> getAllUsers(@RequestParam(required = false) String name) {
        try {
            List<Users> users = new ArrayList<Users>();
            if (name == null){
                userRepository.findAll();
            }
            else {
                userRepository.findByNameContaining(name).forEach(users::add);
            }

            if (users.isEmpty()){
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(users, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // create new user
    @PostMapping("/users")
    public ResponseEntity<Users> addUser(@RequestBody Users user) {
        try {
            Users newUser = userRepository.
                    save(new Users(user.getName(), user.getEmail()));
            return new ResponseEntity<>(newUser, HttpStatus.CREATED);
        } catch(Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // retrieve a user by id
    @GetMapping("/users/{id}")
    public ResponseEntity<Users> getUserById(@PathVariable long id) {
        Optional<Users> userOptional = userRepository.findById(id);
        if (userOptional.isPresent()){
            return new ResponseEntity<>(userOptional.get(), HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // update a user by id
    @PutMapping("/users/{id}")
    public ResponseEntity<Users> updateUserById(@PathVariable long id, @RequestBody Users user) {
        Optional<Users> userOptional = userRepository.findById(id);

        if (userOptional.isPresent()){
            Users userToUpdate = userOptional.get();
            userToUpdate.setName(user.getName());
            userToUpdate.setEmail(user.getEmail());
            return new ResponseEntity<>(userRepository.save(userToUpdate), HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // delete a user by id
    @DeleteMapping("/users/{id}")
    public ResponseEntity<HttpStatus> deleteUserById(@PathVariable long id) {
        try {
            userRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Delete all
    @DeleteMapping("/users")
    public ResponseEntity<HttpStatus> deleteAllUsers() {
        try {
            userRepository.deleteAll();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
}
