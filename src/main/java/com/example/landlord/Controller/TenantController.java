package com.example.landlord.Controller;

import com.example.landlord.entitiy.LandlordDetails;
import com.example.landlord.entitiy.TenantDetails;
import com.example.landlord.service.LandlordService;
import com.example.landlord.service.TenantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/tenants")
public class TenantController implements ErrorController {


        @Autowired
        private TenantService tenantService;

        @PostMapping("/register")
        public ResponseEntity TenantRegister(@RequestBody TenantDetails tenantDetails){
            return tenantService.saveTenatDetails(tenantDetails);
        }
}
