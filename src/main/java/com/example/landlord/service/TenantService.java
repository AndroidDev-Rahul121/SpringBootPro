package com.example.landlord.service;

import com.example.landlord.Repo.LandlordDetailsRepo;
import com.example.landlord.Repo.TenantDetailsRepo;
import com.example.landlord.entitiy.LandlordDetails;
import com.example.landlord.entitiy.TenantDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class TenantService {


    @Autowired
    private TenantDetailsRepo tenantDetailsRepo;
    @Autowired
    private PasswordEncoder passwordEncoder;


    //
    public ResponseEntity saveTenatDetails(TenantDetails tenantDetails){

        TenantDetails tenantDetails1= new TenantDetails(
                tenantDetails.getTenant_id(),
                tenantDetails.getTenant_name(),
                tenantDetails.getEmail(),
                passwordEncoder.encode(tenantDetails.getPassword())
//                employee.getPassword()

        );
//
        tenantDetailsRepo.save(tenantDetails1);
        return new ResponseEntity( tenantDetails.getTenant_name()+"Thank You for successful Registration", HttpStatus.OK);

    }
}
