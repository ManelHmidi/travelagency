package com.ditraacademy.travelagency.core;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class controller {

    @Autowired
    UserRepository userRepository;

    @PostMapping("/user")
    public void createUser (@RequestBody User user){
        userRepository.save(user);
    }

    @GetMapping ("/users")
    public List<User> getUsers (){

        return userRepository.findAll();
    }
//method delete user from the database
    @DeleteMapping("/users/{id}")
    public void DeleteUser(@PathVariable int id){
        userRepository.deleteById(id);
    }

    //method update user by ID
    @PutMapping("/users/{id}")
     public void UpdateUserById(@PathVariable int id, @RequestBody User updatedUser){
       Optional<User> userOptional=userRepository.findById(id);
       User dataBaseUser=userOptional.get();

        dataBaseUser.setName(updatedUser.getName());
        dataBaseUser.setAge(updatedUser.getAge());

       userRepository.save(dataBaseUser);
    }

}
