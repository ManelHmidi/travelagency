package com.ditraacademy.travelagency.core.hotel;

import com.ditraacademy.travelagency.core.chambre.chambres.Chambre;
import com.ditraacademy.travelagency.core.chambre.chambres.ChambreRepository;
import com.ditraacademy.travelagency.utils.ErrorResponseModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HotelServices {
    @Autowired
    HotelRepository hotelRepository;
    @Autowired
    ChambreRepository chambreRepository;

    public ResponseEntity<? extends Object> createHotel (Hotel hotel){

        for (Chambre chambre : hotel.getChambres()){
            Optional<Chambre> chambreOptional=chambreRepository.findById(chambre.getId());
            if (!chambreOptional.isPresent()){
                return new ResponseEntity<>(new ErrorResponseModel("chambre not found: " + chambre.getId()),HttpStatus.BAD_REQUEST);
            }
        }
        hotel=hotelRepository.save(hotel);
        return new ResponseEntity<>(hotel,HttpStatus.OK);

    }

    public List<Hotel> getHotels (){
        return hotelRepository.findAll();
    }


    public ResponseEntity<ErrorResponseModel> UpdateHotelById(int id, Hotel updatedHotel) {
        Optional<Hotel> hotelOptional = hotelRepository.findById(id);

        if (!hotelOptional.isPresent()) {
            ErrorResponseModel errorResponseModel = new ErrorResponseModel("Hotel not found");
            return new ResponseEntity<>(errorResponseModel, HttpStatus.BAD_REQUEST);
        }
        Hotel dataBaseHotel = hotelOptional.get();

        if (updatedHotel.getNom() != null)
            dataBaseHotel.setNom(updatedHotel.getNom());

        if (updatedHotel.getAdresse() != null)
            dataBaseHotel.setAdresse(updatedHotel.getAdresse());

        if (updatedHotel.getEtoiles() != null)
            dataBaseHotel.setEtoiles(updatedHotel.getEtoiles());

        if (updatedHotel.getDescription() != null)
            dataBaseHotel.setDescription(updatedHotel.getDescription());

        if (updatedHotel.getTelephone() != null)
            dataBaseHotel.setTelephone(updatedHotel.getTelephone());

        if (updatedHotel.getChambres() != null) {
            for (Chambre chambre : updatedHotel.getChambres()) {

                Optional<Chambre> chambreOptional = chambreRepository.findById(chambre.getId());

                if (!chambreOptional.isPresent()) {
                    return new ResponseEntity<>(new ErrorResponseModel("chambre not found"), HttpStatus.BAD_REQUEST);
                }

               if (dataBaseHotel.getChambres().contains(chambre)){
                   return new ResponseEntity<>(new ErrorResponseModel("chambre exists already"), HttpStatus.BAD_REQUEST);
               }

                dataBaseHotel.addChambre(chambre);
            }
       }
        hotelRepository.save(dataBaseHotel);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    public ResponseEntity<?> getOneHotel(int id){
        Optional<Hotel> hotelOptional = hotelRepository.findById(id);


        if (!hotelOptional.isPresent()) {
            ErrorResponseModel errorResponseModel = new ErrorResponseModel("Hotel not found");
            return new ResponseEntity<>(errorResponseModel, HttpStatus.BAD_REQUEST);
        }

        Hotel hotel = hotelOptional.get();
        return new ResponseEntity<>(hotel, HttpStatus.OK);
    }

    public ResponseEntity<?> deleteHotel(int id){
        Optional<Hotel> hotelOptional = hotelRepository.findById(id);
        if (!hotelOptional.isPresent()) {
            ErrorResponseModel errorResponseModel = new ErrorResponseModel("Hotel not found");
            new ResponseEntity<>(errorResponseModel, HttpStatus.BAD_REQUEST);
        }
        hotelRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
