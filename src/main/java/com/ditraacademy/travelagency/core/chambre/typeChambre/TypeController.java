package com.ditraacademy.travelagency.core.chambre.typeChambre;

import com.ditraacademy.travelagency.utils.ErrorResponseModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TypeController {
    @Autowired
    TypeServices typeServices;

    @PostMapping("/type")
    public void createType (@RequestBody TypeChambre type){
        typeServices.createType(type);
    }

    @GetMapping("/types")
    public List<TypeChambre> getTypes (){
        return typeServices.getTypes();
    }

    @PutMapping("/type/{id}")
    public ResponseEntity<ErrorResponseModel> UpdateTypeById(@PathVariable int id, @RequestBody TypeChambre updatedType) {
        return typeServices.UpdateTypeById(id,updatedType);
    }

    @GetMapping("/type/{id}")
    public ResponseEntity<?> getOneType(@PathVariable int id){
        return typeServices.getOneType(id);
    }

    @DeleteMapping ("/type/{id}")
    public ResponseEntity<?> deleteType(@PathVariable int id) {
        return typeServices.deleteType(id);
    }
}
