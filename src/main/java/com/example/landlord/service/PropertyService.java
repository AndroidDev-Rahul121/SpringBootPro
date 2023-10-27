package com.example.landlord.service;

import com.example.landlord.Dto.PropertyDto;
import com.example.landlord.Repo.LandlordDetailsRepo;
import com.example.landlord.Repo.PropertyDetailRepo;
import com.example.landlord.entitiy.Address;
import com.example.landlord.entitiy.LandlordDetails;
import com.example.landlord.entitiy.PropertyDetails;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PropertyService {
    @Autowired
    private PropertyDetailRepo propertyDetailRepo;
    @Autowired
    private LandlordDetailsRepo landlordDetailsRepo;


    public ResponseEntity SavePropertyDetails(PropertyDetails propertyDetails){
        Integer landlord_id = Integer.parseInt(propertyDetails.getLandlordPriId());
        System.out.println(propertyDetails);
        Optional<LandlordDetails> landlordDetails = landlordDetailsRepo.findById(landlord_id);
        if (landlordDetails != null) {
            propertyDetails.setLandlord(landlordDetails.get());
            return new  ResponseEntity( propertyDetailRepo.save(propertyDetails), HttpStatus.OK);


        } else {
            return new ResponseEntity("Gives valid landlord Id", HttpStatus.BAD_REQUEST);
        }
//        propertyDetailRepo.save(propertyDetails);
//        return new  ResponseEntity( propertyDetailRepo.save(propertyDetails), HttpStatus.OK);


    }

    public ResponseEntity<List<PropertyDto>> AllPropertyDetails(int id){

        List<PropertyDetails> properties = propertyDetailRepo.findBylandlordPriId(String.valueOf(id));
//        PropertyDto propertyDto =
//        if ()

        // Map the properties to PropertyDTO objects
        List<PropertyDto> propertyDTOs = properties.stream()
                .map(property -> {
                    PropertyDto dto = new PropertyDto();
                    // Create a new Address object and populate it
//                    Address address = new Address();
//                    Address propertyAddress = property.getPropertyAddress();

//                    if (propertyAddress != null) {
////                        address);
//
//                        // Set other properties as needed
//                    }
                    dto.setProperty_id(property.getPropertyId());
                    dto.setLandlordPri_id(property.getLandlordPriId());
                    dto.setProperty_type(property.getPropertyType());
                    dto.setFurnishing_type(property.getFurnishingType());
//
                    // Set the populated Address in PropertyDto
                    dto.setProperty_address(property.getPropertyAddress());


                    return dto;
                })
                .collect(Collectors.toList());

        return new ResponseEntity<>(propertyDTOs,HttpStatus.OK);
//        return new ResponseEntity<>(propertyDetailRepo.findBylandlordPri_id(String.valueOf(id)),HttpStatus.OK);
    }


}
