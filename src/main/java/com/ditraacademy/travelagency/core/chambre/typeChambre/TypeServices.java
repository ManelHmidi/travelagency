package com.ditraacademy.travelagency.core.chambre.typeChambre;

import com.ditraacademy.travelagency.utils.ErrorResponseModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

@Service
public class TypeServices {
    @Autowired
    TypeRepository typeRepository;

    public void createType (TypeChambre type){

        type=typeRepository.save(type);
    }

    public List<TypeChambre> getTypes (){
        return typeRepository.findAll();
    }

    public ResponseEntity<ErrorResponseModel> UpdateTypeById(int id, TypeChambre updatedType) {
        Optional<TypeChambre> typeOptional = typeRepository.findById(id);

        if (!typeOptional.isPresent()) {
            ErrorResponseModel errorResponseModel = new ErrorResponseModel("Type not found");
            return new ResponseEntity<>(errorResponseModel, HttpStatus.BAD_REQUEST);
        }
        TypeChambre dataBaseType = typeOptional.get();

        if (updatedType.getType()!= null)
            dataBaseType.setType(updatedType.getType());

        if (updatedType.getDescription() != null)
            dataBaseType.setDescription(updatedType.getDescription());

        typeRepository.save(dataBaseType);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    public ResponseEntity<?> getOneType(int id){
        Optional<TypeChambre> typeOptional = typeRepository.findById(id);


        if (!typeOptional.isPresent()) {
            ErrorResponseModel errorResponseModel = new ErrorResponseModel("Type not found");
            return new ResponseEntity<>(errorResponseModel, HttpStatus.BAD_REQUEST);
        }

        TypeChambre type = typeOptional.get();
        return new ResponseEntity<>(type, HttpStatus.OK);
    }

    public ResponseEntity<?> deleteType(int id){
        Optional<TypeChambre> typeOptional = typeRepository.findById(id);
        if (!typeOptional.isPresent()) {
            ErrorResponseModel errorResponseModel = new ErrorResponseModel("Type not found");
            return new ResponseEntity<>(errorResponseModel, HttpStatus.BAD_REQUEST);
        }
        typeRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
