package com.ditraacademy.travelagency.core.chambre.categorieChambre;

import com.ditraacademy.travelagency.core.chambre.chambres.Chambre;
import com.ditraacademy.travelagency.core.chambre.typeChambre.TypeChambre;
import com.ditraacademy.travelagency.utils.ErrorResponseModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategorieServices {

    @Autowired
    CategorieRepository categorieRepository;

    public void createCategorie (Categorie categorie){

        categorie=categorieRepository.save(categorie);
    }

    public List<Categorie> getCategories (){
        return categorieRepository.findAll();
    }


    public ResponseEntity<ErrorResponseModel> UpdateCategorieById(int id, Categorie updatedCategorie) {
        Optional<Categorie> categorieOptional = categorieRepository.findById(id);

        if (!categorieOptional.isPresent()) {
            ErrorResponseModel errorResponseModel = new ErrorResponseModel("Category not found");
            return new ResponseEntity<>(errorResponseModel, HttpStatus.BAD_REQUEST);
        }
        Categorie dataBaseCategorie = categorieOptional.get();

        if (updatedCategorie.getCategorie()!= null)
            dataBaseCategorie.setCategorie(updatedCategorie.getCategorie());

        if (updatedCategorie.getDescription() != null)
            dataBaseCategorie.setDescription(updatedCategorie.getDescription());

        categorieRepository.save(dataBaseCategorie);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    public ResponseEntity<?> getOneCategorie(int id){
        Optional<Categorie> categorieOptional = categorieRepository.findById(id);


        if (!categorieOptional.isPresent()) {
            ErrorResponseModel errorResponseModel = new ErrorResponseModel("Category not found");
            return new ResponseEntity<>(errorResponseModel, HttpStatus.BAD_REQUEST);
        }

        Categorie categorie = categorieOptional.get();
        return new ResponseEntity<>(categorie, HttpStatus.OK);
    }

    public ResponseEntity<?> deleteCategorie(int id){
        Optional<Categorie> categorieOptional = categorieRepository.findById(id);
        if (!categorieOptional.isPresent()) {
            ErrorResponseModel errorResponseModel = new ErrorResponseModel("Category not found");
            return new ResponseEntity<>(errorResponseModel, HttpStatus.BAD_REQUEST);
        }
        categorieRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
