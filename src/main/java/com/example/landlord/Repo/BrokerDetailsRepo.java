package com.example.landlord.Repo;

import com.example.landlord.entitiy.BrokerDetails;
import com.example.landlord.entitiy.LandlordDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@EnableJpaRepositories
@Repository
public interface BrokerDetailsRepo  extends JpaRepository<BrokerDetails, Integer> {


        Optional<BrokerDetails> findByEmailAndPassword(String email, String password);
    BrokerDetails findByEmail(String email);
    }

