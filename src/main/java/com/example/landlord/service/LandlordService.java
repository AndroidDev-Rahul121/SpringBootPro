package com.example.landlord.service;

import com.example.landlord.Dto.LoginDto;
import com.example.landlord.Repo.LandlordDetailsRepo;
import com.example.landlord.entitiy.LandlordDetails;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LandlordService {
    @Autowired
    private LandlordDetailsRepo landlordDetailsRepo;
    @Autowired
    private PasswordEncoder passwordEncoder;


//
    public ResponseEntity saveEmployeeDetails(LandlordDetails landlordDetails){

        LandlordDetails landlordDetails1= new LandlordDetails(
                landlordDetails.getLandlord_id(),
               landlordDetails.getLandlord_name(),
                landlordDetails.getEmail(),
                passwordEncoder.encode(landlordDetails.getPassword())
//                employee.getPassword()

        );
//
                landlordDetailsRepo.save(landlordDetails1);
         return new ResponseEntity( landlordDetails.getLandlord_name()+"Thank You for successful Registration", HttpStatus.OK);

    }

    public  String login(LoginDto loginDto) {
LandlordDetails landlordDetails1=landlordDetailsRepo.findByEmail(loginDto.getEmail());
        if (landlordDetails1 != null) {
            String password = loginDto.getPassword();
            String encodedPassword = landlordDetails1.getPassword();
            Boolean isRightPass = passwordEncoder.matches(password,encodedPassword);
            if (isRightPass){
                Optional<LandlordDetails> landlordDetails = landlordDetailsRepo.findByEmailAndPassword(loginDto.getEmail(),encodedPassword);
                if (landlordDetails.isPresent()){
                    return "login sucessfully";
                }else {
                    return  "login failed";
                }
            }else {
                return  "password not match";
            }

        }else {
            return "email not here";
        }

    }

    public LandlordDetails getLandlordById(Integer landlordId) {
        // Implement logic to retrieve a landlord by ID from your repository
        return landlordDetailsRepo.findById(landlordId).orElse(null);
    }
}
