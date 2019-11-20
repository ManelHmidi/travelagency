package com.ditraacademy.travelagency.core.destination;
import com.ditraacademy.travelagency.core.user.UserRepository;
import com.ditraacademy.travelagency.utils.ErrorResponseModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class DestinationController {

    @Autowired
    UserRepository DestinationRepository;

    @Autowired
    DestinationServices destinationServices;

    @PostMapping("/destination")
    public ResponseEntity<?> createDestinations (@RequestBody Destination destination){
        return destinationServices.createDestinations(destination);
    }

    @GetMapping("/destinations")
    public List<Destination> getDestinations (){
        return destinationServices.getDestinations();
    }

    @PutMapping("/destination/{id}")
    public ResponseEntity<ErrorResponseModel> UpdateDestinationById(@PathVariable int id, @RequestBody Destination updatedDestination) {
        return destinationServices.UpdateDestinationById(id,updatedDestination);
    }

    @GetMapping("/destination/{id}")
    public ResponseEntity<?> getOneDestination(@PathVariable int id){
        return destinationServices.getOneDestination(id);
    }

    @DeleteMapping ("/destinations/{id}")
    public ResponseEntity<?> deleteDestination(@PathVariable int id) {
        return destinationServices.deleteDestination(id);
    }
}
