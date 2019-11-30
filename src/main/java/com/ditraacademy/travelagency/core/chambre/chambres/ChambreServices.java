package com.ditraacademy.travelagency.core.chambre.chambres;

import com.ditraacademy.travelagency.core.chambre.categorieChambre.CategorieChambre;
import com.ditraacademy.travelagency.core.chambre.categorieChambre.CategorieChambreRepository;
import com.ditraacademy.travelagency.core.chambre.typeChambre.TypeChambre;
import com.ditraacademy.travelagency.core.chambre.typeChambre.TypeChambreRepository;
import com.ditraacademy.travelagency.utils.ErrorResponseModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ChambreServices {

    @Autowired
    ChambreRepository chambreRepository;
    @Autowired
    CategorieChambreRepository categorieChambreRepository;
    @Autowired
    TypeChambreRepository typeChambreRepository;

    public ResponseEntity<?> createChambre (Chambre chambre){
        Optional<CategorieChambre> categorieChambreOptional = categorieChambreRepository.findById(chambre.getCategorieChambre().getId());

        if (! categorieChambreOptional.isPresent()){
            return new ResponseEntity<>(new ErrorResponseModel("categorie not found"), HttpStatus.BAD_REQUEST);
        }

        Optional<TypeChambre> typeChambreOptional=typeChambreRepository.findById(chambre.getTypeChambre().getId());

        if (!typeChambreOptional.isPresent()){
            return new ResponseEntity<>(new ErrorResponseModel("type not found"), HttpStatus.BAD_REQUEST);
        }
        Optional<Chambre> chambreOptional =chambreRepository.findByCategorieChambreAndTypeChambre(categorieChambreOptional.get(), typeChambreOptional.get());

        if (chambreOptional.isPresent()){
            return new ResponseEntity<>(new ErrorResponseModel("chambre exist"), HttpStatus.BAD_REQUEST);
        }
        chambre=chambreRepository.save(chambre);

//        try {
//            chambre=chambreRepository.save(chambre);
//        }catch (Exception exception){
//            return new ResponseEntity<>(new ErrorResponseModel(exception.getMessage()),HttpStatus.BAD_REQUEST);
//        }
        return new ResponseEntity<>(chambre,HttpStatus.OK);
    }

    public List<Chambre> getChambres (){
        return chambreRepository.findAll();
    }
}
