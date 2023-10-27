package com.example.landlord.entitiy;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name = "Tenant_Details")
public class TenantDetails {

    @Id
    @GeneratedValue
    @Column(name = "tenant_id")
    private int tenant_id;
    @Column(name = "tenant_name")
    private String tenant_name;
    @Column(name = "email")
    private String email;
    @Column(name = "password")
    private String password;
}
