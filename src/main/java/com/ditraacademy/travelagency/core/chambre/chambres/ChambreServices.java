package com.ditraacademy.travelagency.core.chambre.chambres;

import com.ditraacademy.travelagency.core.chambre.categorieChambre.Categorie;
import com.ditraacademy.travelagency.core.chambre.categorieChambre.CategorieRepository;
import com.ditraacademy.travelagency.core.chambre.typeChambre.TypeChambre;
import com.ditraacademy.travelagency.core.chambre.typeChambre.TypeRepository;
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
    CategorieRepository categorieChambreRepository;
    @Autowired
    TypeRepository typeChambreRepository;

    public ResponseEntity<?> createChambre (Chambre chambre){
        Optional<Categorie> categorieChambreOptional = categorieChambreRepository.findById(chambre.getCategorieChambre().getId());

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


    public ResponseEntity<ErrorResponseModel> UpdateChambreById(int id, Chambre updatedChambre) {
        Optional<Chambre> chambreOptional = chambreRepository.findById(id);

        if (!chambreOptional.isPresent()) {
            ErrorResponseModel errorResponseModel = new ErrorResponseModel("chamber not found");
            new ResponseEntity<>(errorResponseModel, HttpStatus.BAD_REQUEST);
        }
        Chambre dataBaseChambre = chambreOptional.get();

        if (updatedChambre.getCategorieChambre()!= null)
            dataBaseChambre.setCategorieChambre(updatedChambre.getCategorieChambre());

        if (updatedChambre.getTypeChambre() != null)
            dataBaseChambre.setTypeChambre(updatedChambre.getTypeChambre());

        chambreRepository.save(dataBaseChambre);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    public ResponseEntity<?> getOneChambre(int id){
        Optional<Chambre> chambreOptional = chambreRepository.findById(id);


        if (!chambreOptional.isPresent()) {
            ErrorResponseModel errorResponseModel = new ErrorResponseModel("Chamber not found");
            new ResponseEntity<>(errorResponseModel, HttpStatus.BAD_REQUEST);
        }

        Chambre chambre = chambreOptional.get();
        return new ResponseEntity<>(chambre, HttpStatus.OK);
    }

    public ResponseEntity<?> deleteChambre(int id){
        Optional<Chambre> chambreOptional = chambreRepository.findById(id);
        if (!chambreOptional.isPresent()) {
            ErrorResponseModel errorResponseModel = new ErrorResponseModel("Chamber not found");
            new ResponseEntity<>(errorResponseModel, HttpStatus.BAD_REQUEST);
        }
        chambreRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
