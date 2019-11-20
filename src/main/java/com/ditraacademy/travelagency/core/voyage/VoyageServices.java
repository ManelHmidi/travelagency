package com.ditraacademy.travelagency.core.voyage;


import com.ditraacademy.travelagency.core.destination.Destination;
import com.ditraacademy.travelagency.core.destination.DestinationRepository;
import com.ditraacademy.travelagency.utils.ErrorResponseModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VoyageServices {
    @Autowired
    VoyageRepository voyageRepository;
    @Autowired
    DestinationRepository destinationRepository;

    public ResponseEntity<?> createVoyage (Voyage voyage){

       Optional<Destination> destinationOptional= destinationRepository.findById(voyage.getDestination().getId());
       if(!destinationOptional.isPresent()){
           ErrorResponseModel errorResponseModel = new ErrorResponseModel("Destination not found");
           new ResponseEntity<>(errorResponseModel, HttpStatus.BAD_REQUEST);
       }
       voyage.setDestination(destinationOptional.get());
        voyage=voyageRepository.save(voyage);
        return new ResponseEntity<>(voyage, HttpStatus.OK);
    }

    public List<Voyage> getVoyages (){
        return voyageRepository.findAll();
    }

    public ResponseEntity<?> updateVoyageById(int id, Voyage updatedVoyage) {
        Optional<Voyage> voyageOptional = voyageRepository.findById(id);

        if (!voyageOptional.isPresent()) {
            ErrorResponseModel errorResponseModel = new ErrorResponseModel("Voyage not found");
            new ResponseEntity<>(errorResponseModel, HttpStatus.BAD_REQUEST);
        }
        Voyage dataBaseVoyage = voyageOptional.get();

        if (updatedVoyage.getTitre()!= null)
            dataBaseVoyage.setTitre(updatedVoyage.getTitre());

        if (updatedVoyage.getDate()!= null)
            dataBaseVoyage.setDate(updatedVoyage.getDate());

        if (updatedVoyage.getDescription()!= null)
            dataBaseVoyage.setDescription(updatedVoyage.getDescription());

        if (updatedVoyage.getNbPlaces()!= null)
            dataBaseVoyage.setNbPlaces(updatedVoyage.getNbPlaces());

        if (updatedVoyage.getPrix()!= null)
            dataBaseVoyage.setPrix(updatedVoyage.getPrix());

        voyageRepository.save(dataBaseVoyage);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    public ResponseEntity<?> getOneVoyage(int id){
        Optional<Voyage> voyageOptional = voyageRepository.findById(id);

        if (!voyageOptional.isPresent()) {
            ErrorResponseModel errorResponseModel = new ErrorResponseModel("Voyage not found");
            new ResponseEntity<>(errorResponseModel, HttpStatus.BAD_REQUEST);
        }

        Voyage voyage = voyageOptional.get();
        return new ResponseEntity<>(voyage, HttpStatus.OK);
    }

    public ResponseEntity<?> deleteVoyage(int id){
        Optional<Voyage> voyageOptional = voyageRepository.findById(id);

        if (!voyageOptional.isPresent()) {
            ErrorResponseModel errorResponseModel = new ErrorResponseModel("Voyage not found");
            new ResponseEntity<>(errorResponseModel, HttpStatus.BAD_REQUEST);
        }

        voyageRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    public List<Voyage> getVoyagesByPrice(float min, float max) {
        List<Voyage> voyageList = voyageRepository.findAllByPrixIsBetweenAndNbPlacesNot(min, max,0);
        return voyageList;
    }

    public List<Voyage> getVoyageByPrice(float price, int nbPlaces) {
        List<Voyage> voyageList = voyageRepository.findAllByQuery(price,nbPlaces);
        return voyageList;
    }
}