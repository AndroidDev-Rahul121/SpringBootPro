package com.example.landlord.Repo;


import com.example.landlord.entitiy.LandlordDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@EnableJpaRepositories
@Repository
public interface LandlordDetailsRepo extends JpaRepository<LandlordDetails, Integer> {


    Optional<LandlordDetails> findByEmailAndPassword(String email, String password);
    LandlordDetails findByEmail(String email);
}
