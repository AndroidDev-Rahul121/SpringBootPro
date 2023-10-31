package com.example.landlord.Controller;

import com.example.landlord.entitiy.BrokerDetails;
import com.example.landlord.entitiy.TenantDetails;
import com.example.landlord.service.BrokerService;
import com.example.landlord.service.TenantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController
@RequestMapping("/brokers")
public class BrokerController implements ErrorController {


        @Autowired
        private BrokerService brokerService;

        @PostMapping("/register")
        public ResponseEntity BrokerRegister(@RequestBody BrokerDetails brokerDetails){
            return brokerService.saveBrokerDetails(brokerDetails);
        }
    }


