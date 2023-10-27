package com.example.landlord.Repo;

import com.example.landlord.entitiy.FrientdRequest;
import com.example.landlord.entitiy.TenantDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@EnableJpaRepositories
@Repository
public interface FriendRequestRepo extends JpaRepository<FrientdRequest, Integer> {
    List<FrientdRequest> findByIdAndStatus(int tenantId, String status);
//    / Custom query to retrieve friend requests for a given tenant ID
//    List<FrientdRequest> findByTenantId(int tenantId);

    // Custom query to retrieve friend requests for a given tenant based on the tenant field
    //List<FrientdRequest> findByTenant(Optional<TenantDetails> tenant);
    @Query(value = "SELECT * FROM friendrequest_tbl where tenant_id = ?1", nativeQuery = true)
    List<FrientdRequest> findByTenantDetails(int tenantId);


    @Query(value = "SELECT * FROM friendrequest_tbl where tenant_id = ?1", nativeQuery = true)
    List<FrientdRequest> findByTenantDetailsAndStatusAndRecever(int tenantId,String status,String recever);
 @Query(value = "SELECT * FROM friendrequest_tbl where broker_id = ?1", nativeQuery = true)
    List<FrientdRequest> findByBrokerDetails(int brokerId);

    boolean existsBySenderIdAndReceverIdAndStatusAndSenderAndRecever(int senderId, int receiverId, String pending,String sender,String recever);
//    boolean existsBySenderAndReceiverAndStatus(int senderId, int receiverId, String pending);
}
