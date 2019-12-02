package com.ditraacademy.travelagency.core.chambre.chambres;

import com.ditraacademy.travelagency.core.destination.Destination;
import com.ditraacademy.travelagency.utils.ErrorResponseModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ChambreController {

    @Autowired
    ChambreServices chambreServices;
    @Autowired
    ChambreRepository chambreRepository;

    @PostMapping("/chambre")
    public void createChambre (@RequestBody Chambre chambre){
        chambreServices.createChambre(chambre);
    }

    @GetMapping("/chambres")
    public List<Chambre> getChambres (){
        return chambreServices.getChambres();
    }

    @PutMapping("/chambre/{id}")
    public ResponseEntity<ErrorResponseModel> UpdateChambreById(@PathVariable int id, @RequestBody Chambre updatedChambre) {
        return chambreServices.UpdateChambreById(id,updatedChambre);
    }

    @GetMapping("/chambre/{id}")
    public ResponseEntity<?> getOneChambre(@PathVariable int id){
        return chambreServices.getOneChambre(id);
    }

    @DeleteMapping ("/chambres/{id}")
    public ResponseEntity<?> deleteChambre(@PathVariable int id) {
        return chambreServices.deleteChambre(id);
    }
}
