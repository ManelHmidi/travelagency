package com.ditraacademy.travelagency.core.user;

import com.ditraacademy.travelagency.utils.ErrorResponseModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServices {

    @Autowired
    UserRepository userRepository;

    public ResponseEntity<?> createUsers (User user){

        if (user.getName() == null)
            return new ResponseEntity<>(new ErrorResponseModel("user name Required"), HttpStatus.BAD_REQUEST);
        if (user.getName().length() <3)
            return new ResponseEntity<>(new ErrorResponseModel("Wrong user name"),HttpStatus.BAD_REQUEST);
        if (user.getAge() == null)
            return new ResponseEntity<>(new ErrorResponseModel("User age required"),HttpStatus.BAD_REQUEST);
        if (user.getAge() <0 || user.getAge() >200)
            return new ResponseEntity<>(new ErrorResponseModel("wrong user age"),HttpStatus.BAD_REQUEST);

        user = userRepository.save(user);
        return new ResponseEntity<>(user,HttpStatus.OK);
    }

    public List<User> getUsers (){
        return userRepository.findAll();
    }

    public ResponseEntity<ErrorResponseModel> UpdateUserById(int id, User updatedUser) {
        Optional<User> userOptional = userRepository.findById(id);


        if (!userOptional.isPresent()) {
            ErrorResponseModel errorResponseModel = new ErrorResponseModel("user not found");
            new ResponseEntity<>(errorResponseModel, HttpStatus.BAD_REQUEST);
        }
        User dataBaseUser = userOptional.get();

        if (updatedUser.getName() != null) {
            if (updatedUser.getName().length() < 3)
                return new ResponseEntity<>(new ErrorResponseModel("Wrong user name"), HttpStatus.BAD_REQUEST);
            dataBaseUser.setName(updatedUser.getName());
        }

        if (updatedUser.getAge() != null) {
            if (updatedUser.getAge() < 0 || updatedUser.getAge() > 200)
                return new ResponseEntity<>(new ErrorResponseModel("wrong user age"), HttpStatus.BAD_REQUEST);

            dataBaseUser.setAge(updatedUser.getAge());
        }
        userRepository.save(dataBaseUser);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    public ResponseEntity<?> getOneUser(int id){
        Optional<User> userOptional= userRepository.findById(id);

        if (userOptional.isPresent()){
            User user = userOptional.get();
            return new ResponseEntity<>(user, HttpStatus.OK);
        }else {
            ErrorResponseModel errorResponseModel = new ErrorResponseModel("user not found");
            return new ResponseEntity<>(errorResponseModel,HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<?> deleteUser(int id){
        Optional<User> userOptional=userRepository.findById(id);

        if (!userOptional.isPresent()){
            ErrorResponseModel errorResponseModel = new ErrorResponseModel("user not found");
            new ResponseEntity<>(errorResponseModel,HttpStatus.BAD_REQUEST);
        }

        userRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    public ResponseEntity<?> getUserByAge (){
        List<User> userList = userRepository.findAllByAgeIsLessThan(20);
       return new ResponseEntity<>(userList, HttpStatus.OK);
    }
}
