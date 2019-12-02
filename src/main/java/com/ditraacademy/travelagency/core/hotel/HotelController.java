package com.ditraacademy.travelagency.core.hotel;

import com.ditraacademy.travelagency.utils.ErrorResponseModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class HotelController {

    @Autowired
    HotelRepository hotelRepository;
    @Autowired
    HotelServices hotelServices;

    @PostMapping("/hotel")
    public void createHotel (@RequestBody Hotel hotel){
        hotelServices.createHotel(hotel);
    }

    @GetMapping("/hotels")
    public List<Hotel> getHotels (){
        return hotelServices.getHotels();
    }

    @PutMapping("/hotel/{id}")
    public ResponseEntity<ErrorResponseModel> UpdateHotelById(@PathVariable int id, @RequestBody Hotel updatedHotel) {
        return hotelServices.UpdateHotelById(id,updatedHotel);
    }

    @GetMapping("/hotel/{id}")
    public ResponseEntity<?> getOneHotel(@PathVariable int id){
        return hotelServices.getOneHotel(id);
    }

    @DeleteMapping ("/hotel/{id}")
    public ResponseEntity<?> deleteHotel(@PathVariable int id) {
        return hotelServices.deleteHotel(id);
    }
}
