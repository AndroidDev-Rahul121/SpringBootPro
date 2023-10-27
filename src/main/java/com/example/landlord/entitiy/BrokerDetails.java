package com.example.landlord.entitiy;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name = "Broker_Details")
public class BrokerDetails {

    @Id
    @GeneratedValue
    @Column(name = "broker_id")
    private int broker_id;
    @Column(name = "broker_name")
    private String broker_name;
    @Column(name = "email")
    private String email;
    @Column(name = "password")
    private String password;
}
