package com.example.landlord.entitiy;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "Proerty_Details")
public class PropertyDetails {
    @Id
    @GeneratedValue
    private int propertyId;
    private String propertyType;
    private String propertyArea;
    private String bathroomNo;
    private String furnishingType;
    private String floorType;
    private String parkingType;
    private String landlordPriId;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "ProAddId")
    private Address propertyAddress;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "landlord_id")
    private LandlordDetails landlord;

}
