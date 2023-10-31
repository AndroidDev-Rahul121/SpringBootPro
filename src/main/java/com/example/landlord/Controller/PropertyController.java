package com.example.landlord.Controller;

import com.example.landlord.entitiy.LandlordDetails;
import com.example.landlord.entitiy.PropertyDetails;
import com.example.landlord.service.LandlordService;
import com.example.landlord.service.PropertyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/propeties")
public class PropertyController implements ErrorController {
    @Autowired
    private PropertyService propertyService;
    @Autowired
    private LandlordService landlordService;

    @PostMapping("/add")
    public ResponseEntity AddPropeerty(@RequestBody PropertyDetails propertyDetails) {
//
        return new ResponseEntity(propertyService.SavePropertyDetails(propertyDetails),HttpStatus.OK);
    }

@GetMapping("/{id}")
    public  ResponseEntity AllPropertyDetails(@PathVariable int id){

        return new ResponseEntity(propertyService.AllPropertyDetails(id),HttpStatus.OK);

    }

}
