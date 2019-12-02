package com.ditraacademy.travelagency.core.chambre.categorieChambre;

import com.ditraacademy.travelagency.utils.ErrorResponseModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CategorieController {

    @Autowired
    CategorieServices categorieServices;

    @PostMapping("/categorie")
    public void createCategorie (@RequestBody Categorie categorie){
        categorieServices.createCategorie(categorie);
    }

    @GetMapping("/categories")
    public List<Categorie> getCategories (){
        return categorieServices.getCategories();
    }

    @PutMapping("/categorie/{id}")
    public ResponseEntity<ErrorResponseModel> UpdateCategorieById(@PathVariable int id, @RequestBody Categorie updatedCategorie) {
        return categorieServices.UpdateCategorieById(id,updatedCategorie);
    }

    @GetMapping("/categorie/{id}")
    public ResponseEntity<?> getOneCategorie(@PathVariable int id){
        return categorieServices.getOneCategorie(id);
    }

    @DeleteMapping ("/categorie/{id}")
    public ResponseEntity<?> deleteCategorie(@PathVariable int id) {
        return categorieServices.deleteCategorie(id);
    }
}
