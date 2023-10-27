package com.example.landlord.service;

import com.example.landlord.Dto.FriendRequesResDto;
import com.example.landlord.Dto.FrientRequestDto;
import com.example.landlord.Repo.BrokerDetailsRepo;
import com.example.landlord.Repo.FriendRequestRepo;
import com.example.landlord.Repo.LandlordDetailsRepo;
import com.example.landlord.Repo.TenantDetailsRepo;
import com.example.landlord.actoinresponse.ApiResponse;
import com.example.landlord.entitiy.BrokerDetails;
import com.example.landlord.entitiy.FrientdRequest;
import com.example.landlord.entitiy.LandlordDetails;
import com.example.landlord.entitiy.TenantDetails;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class FrientRequestService {
    @Autowired
    private FriendRequestRepo friendRequestRepo;

    @Autowired
    private LandlordDetailsRepo landlordDetailsRepo;
    @Autowired
    private TenantDetailsRepo tenantDetailsRepo;

    @Autowired
    private BrokerDetailsRepo brokerDetailsRepo;

    //Post Api for sentRequest
    public ResponseEntity sendFriendRequest(FrientRequestDto frientRequestDto) {
        // Check if both sender and receiver exist
        FrientdRequest friendRequest = new FrientdRequest();
        String senderType = frientRequestDto.getSender_type();
        int senderId = frientRequestDto.getSender_id();

        int receiverId = frientRequestDto.getRecever_id();
        String receiverType = frientRequestDto.getRecever_type();
//         Check if a friend request already exists between the sender and receiver
        if (friendRequestRepo.existsBySenderIdAndReceverIdAndStatusAndSenderAndRecever(senderId, receiverId, "PENDING",senderType,receiverType)) {
//            String s = "Friend request already sent.";
            return ResponseEntity.ok(new ApiResponse("success", "Friend request already sent.", "Friend request already sent."));

//            return new ResponseEntity("Friend request already sent.", HttpStatus.OK);

        } else if (friendRequestRepo.existsBySenderIdAndReceverIdAndStatusAndSenderAndRecever(receiverId, senderId, "PENDING",receiverType,senderType)) {

            return ResponseEntity.ok(new ApiResponse("success", "Already have reques from this user", "Accept Friend request "));


        } else {

            if ("landlord".equals(senderType)) {
                LandlordDetails sender = landlordDetailsRepo.findById(senderId)
                        .orElseThrow(() -> new EntityNotFoundException("Landlord not found"));
                friendRequest.setLandlord(sender);
                friendRequest.setSenderId(senderId);
                friendRequest.setSender("landlord");
            } else if ("tenant".equals(senderType)) {
                TenantDetails sender = tenantDetailsRepo.findById(senderId)
                        .orElseThrow(() -> new EntityNotFoundException("Tenant not found"));
                friendRequest.setTenant(sender);
                friendRequest.setSenderId(senderId);
                friendRequest.setSender("tenant");
            } else if ("broker".equals(senderType)) {
                BrokerDetails sender = brokerDetailsRepo.findById(senderId)
                        .orElseThrow(() -> new EntityNotFoundException("Broker not found"));
                friendRequest.setBroker(sender);
                friendRequest.setSenderId(senderId);
                friendRequest.setSender("broker");

            } else {
                throw new IllegalArgumentException("Invalid sender type");
            }


            // Set the receiver based on the receiver type (tenant or broker)
            if (receiverType.equals("tenant")) {
                TenantDetails receiver = tenantDetailsRepo.findById(receiverId)
                        .orElseThrow(() -> new EntityNotFoundException("Tenant receiver not found"));
                friendRequest.setTenant(receiver);
                friendRequest.setReceverId(receiverId);
                friendRequest.setRecever("tenant");
            } else if (receiverType.equals("broker")) {
                BrokerDetails receiver = brokerDetailsRepo.findById(receiverId)
                        .orElseThrow(() -> new EntityNotFoundException("Broker receiver not found"));
                friendRequest.setBroker(receiver);
                friendRequest.setRecever("broker");
                friendRequest.setReceverId(receiverId);

            } else if (receiverType.equals("landlord")) {
                LandlordDetails receiver = landlordDetailsRepo.findById(receiverId)
                        .orElseThrow(() -> new EntityNotFoundException("landlord receiver not found"));
                friendRequest.setLandlord(receiver);
                friendRequest.setReceverId(receiverId);

                friendRequest.setRecever("landlord");
            } else {
                throw new IllegalArgumentException("Invalid receiver type");
            }

            friendRequest.setStatus("PENDING");

            friendRequestRepo.save(friendRequest);

        }
        return new ResponseEntity("friend request sent", HttpStatus.OK);
//        return ResponseEntity.ok(new ApiResponse("success", "Friend request sent", "friend_request_sent"));

    }


    //Post Api for accept Request
    public void acceptFriendRequest(int requestId) {
        // Find the friend request by its ID
        Optional<FrientdRequest> friendRequestOptional = friendRequestRepo.findById(requestId);

        if (friendRequestOptional.isPresent()) {
            FrientdRequest friendRequest = friendRequestOptional.get();

            // Check if the friend request is in a "PENDING" state
            if ("PENDING".equals(friendRequest.getStatus())) {
                // Update the status to "ACCEPTED"
                friendRequest.setStatus("ACCEPTED");
               friendRequestRepo.save(friendRequest);
            } else {
                throw new IllegalStateException("Friend request is not in a PENDING state.");
            }
        } else {
            throw new EntityNotFoundException("Friend request not found.");
        }
    }



    //Get Api for show request in tenant side
    public List<FriendRequesResDto> getLandlordRequestsToTenant(int tenantId) {

        Optional<TenantDetails> tenant = tenantDetailsRepo.findById(tenantId);


//        }
        List<FriendRequesResDto> tenantReceiverRequests = friendRequestRepo.findByTenantDetailsAndStatusAndRecever(tenantId, "PENDING", "tenant").stream()
                .filter(request -> "tenant".equals(request.getRecever())&&"PENDING".equals(request.getStatus()))
                .map(request -> {
                    FriendRequesResDto friendRequesResDto = new FriendRequesResDto();
friendRequesResDto.setRequestId(request.getId());
                    friendRequesResDto.setId(request.getLandlord() != null ? request.getLandlord().getLandlord_id() : request.getBroker().getBroker_id());
                    friendRequesResDto.setName(request.getLandlord() != null ? request.getLandlord().getLandlord_name() : request.getBroker().getBroker_name());
                    friendRequesResDto.setEmail(request.getLandlord() != null ? request.getLandlord().getEmail() : request.getBroker().getEmail());
                    friendRequesResDto.setProfile(request.getLandlord() != null ? "Landlord":"Broker");

                    return friendRequesResDto;
                })
                .collect(Collectors.toList());




//return friendRequestRepo.findByTenantDetailsAndStatusAndRecever(tenantId,"PENDING","tenant");
return  tenantReceiverRequests;
    }




    //Get Api for show make friends in tenant side
    public List<FriendRequesResDto> getFriendsOfTenant(int tenantId) {

        List<FriendRequesResDto> tenantReceiverRequests = friendRequestRepo.findByTenantDetailsAndStatusAndRecever(tenantId, "PENDING", "tenant").stream()
                .filter(request -> "ACCEPTED".equals(request.getStatus()))//"tenant".equals(request.getRecever())&&
                .map(request -> {
                    FriendRequesResDto friendRequesResDto = new FriendRequesResDto();
                    friendRequesResDto.setRequestId(request.getId());
                    friendRequesResDto.setId(request.getLandlord() != null ? request.getLandlord().getLandlord_id() : request.getBroker().getBroker_id());
                    friendRequesResDto.setName(request.getLandlord() != null ? request.getLandlord().getLandlord_name() : request.getBroker().getBroker_name());
                    friendRequesResDto.setEmail(request.getLandlord() != null ? request.getLandlord().getEmail() : request.getBroker().getEmail());
                    friendRequesResDto.setProfile(request.getLandlord() != null ? "Landlord":"Broker");
                    return friendRequesResDto;
                })
                .collect(Collectors.toList());
        return tenantReceiverRequests;
    }
}