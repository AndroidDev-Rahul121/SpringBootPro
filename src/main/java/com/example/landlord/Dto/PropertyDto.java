package com.example.landlord.Dto;

import com.example.landlord.entitiy.Address;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.CascadeType;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class PropertyDto {
    private int property_id;
    private String landlordPri_id;
    private String property_type;
    private String furnishing_type;

//    @OneToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private  Address property_address;
}
