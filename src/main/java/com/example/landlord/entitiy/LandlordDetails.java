package com.example.landlord.entitiy;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name = "Landlord_Details")
public class LandlordDetails{

    @Id
    @GeneratedValue
    @Column(name = "landlord_id")
    private int landlord_id;
    @Column(name = "landlord_name")
    private String landlord_name;
    @Column(name = "email")
    private String email;
    @Column(name = "password")
    private String password;

}
