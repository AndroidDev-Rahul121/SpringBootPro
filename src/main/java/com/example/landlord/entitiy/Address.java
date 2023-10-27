package com.example.landlord.entitiy;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name = "property_Address")
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "add_Id")
    private int addId;
    private String pincode;
    private String state;
    private String city;
    private String house;
    private String locality;
    private String landmark;
    private String property_type;
}
