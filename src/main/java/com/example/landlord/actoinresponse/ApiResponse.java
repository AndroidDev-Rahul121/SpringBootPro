package com.example.landlord.actoinresponse;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ApiResponse {

    private String status;
    private String message;
    private String action;



}
