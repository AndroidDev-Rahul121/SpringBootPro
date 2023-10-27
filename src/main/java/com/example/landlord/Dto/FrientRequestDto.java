package com.example.landlord.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class FrientRequestDto {

    private int sender_id;
    private String sender_type;
    private int recever_id;
    private String recever_type;

}
