package com.example.landlord.Controller;

import com.example.landlord.Dto.LoginDto;
import com.example.landlord.entitiy.LandlordDetails;
import com.example.landlord.service.LandlordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/landlords")
public class LanlordController implements ErrorController {
    @Autowired
    private LandlordService landlordService;

    @PostMapping("/register")
    public ResponseEntity LandlordRegister(@RequestBody LandlordDetails landlordDetails){
        return landlordService.saveEmployeeDetails(landlordDetails);
    }
    @PostMapping("/login")
    public String Landlordlogin(@RequestBody LoginDto loginDto){
        return landlordService.login(loginDto);
    }
}
