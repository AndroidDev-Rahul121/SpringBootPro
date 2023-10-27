package com.example.landlord.entitiy;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name = "Friendrequest_tbl")
public class FrientdRequest {

    @Id
    @GeneratedValue
    @Column(name = "request_id")
    private int id;

    @ManyToOne
    @JoinColumn(name = "landlord_id")
    private LandlordDetails landlord;

    @ManyToOne
    @JoinColumn(name = "tenant_id")
    private TenantDetails tenant;

    @ManyToOne
    @JoinColumn(name = "broker_id")
    private  BrokerDetails broker;


    // Status can be PENDING, ACCEPTED, REJECTED, etc.
    @Column(name = "status")
    private String status;
    @Column(name = "sender")
    private String sender;

    private int senderId;

    @Column(name = "recever")
    private String recever;

    private int receverId;

}
