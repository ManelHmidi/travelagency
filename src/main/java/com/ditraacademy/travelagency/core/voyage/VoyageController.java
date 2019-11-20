package com.ditraacademy.travelagency.core.voyage;

import com.ditraacademy.travelagency.utils.ErrorResponseModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class VoyageController {

    @Autowired
    VoyageServices voyageServices;

    @PostMapping("/voyage")
    public ResponseEntity<?> createVoyages (@RequestBody Voyage voyage){
        return voyageServices.createVoyage(voyage);
    }

    @GetMapping("/voyages")
    public List<Voyage> getVoyages (){
        return voyageServices.getVoyages();
    }

    @PutMapping("/voyage/{id}")
    public ResponseEntity<?> updateVoyageById(@PathVariable int id, @RequestBody Voyage updatedVoyage) {
        return voyageServices.updateVoyageById(id,updatedVoyage);
    }

    @GetMapping("/voyage/{id}")
    public ResponseEntity<?> getOneVoyage(@PathVariable int id){
        return voyageServices.getOneVoyage(id);
    }

    @DeleteMapping ("/voyage/{id}")
    public ResponseEntity<?> deleteVoyage(@PathVariable int id) {
        return voyageServices.deleteVoyage(id);
    }

    @GetMapping("/voyaget")
    public List<Voyage> getVoyagesByPrice(@RequestParam float valMin ,@RequestParam float valMax){
        return voyageServices.getVoyagesByPrice(valMin,valMax);
    }

    @GetMapping("/voyagess")
    public List<Voyage> getVoyageByPrice(@RequestParam float price,@RequestParam int nbPlaces){
        return voyageServices.getVoyagesByPrice(price,nbPlaces);
    }
}