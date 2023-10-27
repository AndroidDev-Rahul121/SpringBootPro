package com.example.landlord.Repo;

import com.example.landlord.entitiy.PropertyDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.List;

@EnableJpaRepositories
@Repository
public interface PropertyDetailRepo extends JpaRepository<PropertyDetails,Integer> {

List<PropertyDetails> findBylandlordPriId(String landlordPriId);

}
