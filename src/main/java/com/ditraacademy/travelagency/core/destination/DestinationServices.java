package com.ditraacademy.travelagency.core.destination;

import com.ditraacademy.travelagency.core.user.User;
import com.ditraacademy.travelagency.utils.ErrorResponseModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DestinationServices {
    @Autowired
    DestinationRepository destinationRepository;

    public ResponseEntity<?> createDestinations (Destination destination){
        if (destination.getNom() == null)
            return new ResponseEntity<>(new ErrorResponseModel("Destination name Required"), HttpStatus.BAD_REQUEST);
        if (destination.getDescription() == null)
            return new ResponseEntity<>(new ErrorResponseModel("Description Required"), HttpStatus.BAD_REQUEST);
         destination=destinationRepository.save(destination);
        return new ResponseEntity<>(destination, HttpStatus.OK);
    }

    public List<Destination> getDestinations (){
        return destinationRepository.findAll();
    }

    public ResponseEntity<ErrorResponseModel> UpdateDestinationById(int id, Destination updatedDestination) {
        Optional<Destination> destinationOptional = destinationRepository.findById(id);

        if (!destinationOptional.isPresent()) {
            ErrorResponseModel errorResponseModel = new ErrorResponseModel("Destination not found");
            new ResponseEntity<>(errorResponseModel, HttpStatus.BAD_REQUEST);
        }
        Destination dataBaseDestination = destinationOptional.get();

        if (updatedDestination.getNom()!= null)
            dataBaseDestination.setNom(updatedDestination.getNom());

        if (updatedDestination.getDescription() != null)
            dataBaseDestination.setDescription(updatedDestination.getDescription());

        destinationRepository.save(dataBaseDestination);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    public ResponseEntity<?> getOneDestination(int id){
        Optional<Destination> destinationOptional = destinationRepository.findById(id);


        if (!destinationOptional.isPresent()) {
            ErrorResponseModel errorResponseModel = new ErrorResponseModel("Destination not found");
            new ResponseEntity<>(errorResponseModel, HttpStatus.BAD_REQUEST);
        }

        Destination destination = destinationOptional.get();
        return new ResponseEntity<>(destination, HttpStatus.OK);
    }

    public ResponseEntity<?> deleteDestination(int id){
        Optional<Destination> destinationOptional = destinationRepository.findById(id);
        if (!destinationOptional.isPresent()) {
            ErrorResponseModel errorResponseModel = new ErrorResponseModel("Destination not found");
            new ResponseEntity<>(errorResponseModel, HttpStatus.BAD_REQUEST);
        }
        destinationRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
