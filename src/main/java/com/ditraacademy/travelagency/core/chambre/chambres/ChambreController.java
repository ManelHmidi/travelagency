package com.ditraacademy.travelagency.core.chambre.chambres;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Controller
public class ChambreController {

    @Autowired
    ChambreServices chambreServices;
    @Autowired
    ChambreRepository chambreRepository;

    @PostMapping("/chambre")
    public void createChambre (@RequestBody Chambre chambre){
        chambreServices.createChambre(chambre);
    }

    @GetMapping("/chambre")
    public List<Chambre> getChambres (){
        return chambreServices.getChambres();
    }

}
