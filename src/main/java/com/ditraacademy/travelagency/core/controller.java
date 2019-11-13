package com.ditraacademy.travelagency.core;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    @PutMapping("/users/{id}/{name}/{age}")
     public void UpdateUserById(@PathVariable int id, @PathVariable String name, @PathVariable int age){
       User user=userRepository.getOne(id);
       user.setName(name);
       user.setAge(age);
       userRepository.save(user);
    }

}
