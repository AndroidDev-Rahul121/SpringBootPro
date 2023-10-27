package com.example.landlord.service;

import com.example.landlord.Repo.BrokerDetailsRepo;
import com.example.landlord.Repo.TenantDetailsRepo;
import com.example.landlord.entitiy.BrokerDetails;
import com.example.landlord.entitiy.TenantDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class BrokerService {

    @Autowired
    private BrokerDetailsRepo brokerDetailsRepo;
    @Autowired
    private PasswordEncoder passwordEncoder;


    //
    public ResponseEntity saveBrokerDetails(BrokerDetails brokerDetails){

        BrokerDetails brokerDetails1= new BrokerDetails(
                brokerDetails.getBroker_id(),
                brokerDetails.getBroker_name(),
                brokerDetails.getEmail(),
                passwordEncoder.encode(brokerDetails.getPassword())
//                employee.getPassword()

        );
//
        brokerDetailsRepo.save(brokerDetails1);
        return new ResponseEntity( brokerDetails.getBroker_name()+"Thank You for successful Registration", HttpStatus.OK);

    }
}
