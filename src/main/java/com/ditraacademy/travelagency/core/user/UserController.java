package com.ditraacademy.travelagency.core.user;

import com.ditraacademy.travelagency.utils.ErrorResponseModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserServices userServices;

    @PostMapping("/user")
    public ResponseEntity<?> createUsers (@RequestBody User user){
        return userServices.createUsers(user);
    }

    @GetMapping ("/users")
    public List<User> getUsers (){
        return userServices.getUsers();
    }

    //method update user by ID
    @PutMapping("/users/{id}")
     public ResponseEntity<ErrorResponseModel> UpdateUserById(@PathVariable int id, @RequestBody User updatedUser) {
        return userServices.UpdateUserById(id, updatedUser);
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<?> getOneUser(@PathVariable int id){
        return userServices.getOneUser(id);
    }

    @DeleteMapping ("user/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable int id) {
       return userServices.deleteUser(id);
    }

    @GetMapping("/userByAge")
    public ResponseEntity<?> getUserByAge (){
       return userServices.getUserByAge();
    }
}