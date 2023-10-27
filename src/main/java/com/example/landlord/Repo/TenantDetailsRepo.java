package com.example.landlord.Repo;

import com.example.landlord.entitiy.LandlordDetails;
import com.example.landlord.entitiy.TenantDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@EnableJpaRepositories
@Repository
public interface TenantDetailsRepo  extends JpaRepository<TenantDetails, Integer> {


        Optional<TenantDetails> findByEmailAndPassword(String email, String password);
    TenantDetails findByEmail(String email);
    }

