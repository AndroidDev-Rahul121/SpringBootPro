package com.example.landlord.Controller;

import com.example.landlord.Dto.FriendRequesResDto;
import com.example.landlord.Dto.FrientRequestDto;
import com.example.landlord.entitiy.FrientdRequest;
import com.example.landlord.service.FrientRequestService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/friendrequests")
public class FriendRequestController {
    @Autowired
    private FrientRequestService frientRequestService;

    @PostMapping
    public ResponseEntity sentfriendRequest(@RequestBody FrientRequestDto frientRequestDto){
        return frientRequestService.sendFriendRequest(frientRequestDto);
//        return new ResponseEntity("", HttpStatus.OK);
    }

    @GetMapping("/sentTotenant/{tenantId}")
    public ResponseEntity<List<FriendRequesResDto>> getLandlordRequestsToTenant(@PathVariable int tenantId) {
        List<FriendRequesResDto> landlordRequests = frientRequestService.getLandlordRequestsToTenant(tenantId);
//        List<FriendRequesResDto> landlordRequests = frientRequestService.getRequestsToBroker(tenantId);
        return new ResponseEntity<>(landlordRequests, HttpStatus.OK);
    }

    @GetMapping("/tenantfriends/{tenantId}")
    public ResponseEntity<List<FriendRequesResDto>> getfriendsOfTenant(@PathVariable int tenantId) {
//        List<FriendRequesResDto> landlordRequests = frientRequestService.getFriendsOfTenant(tenantId,"ACCEPTED");
//        List<FriendRequesResDto> landlordRequests = frientRequestService.getFriendsOfLandlord(tenantId,"ACCEPTED");
        List<FriendRequesResDto> landlordRequests = frientRequestService.getFriendsOfBroker(tenantId,"ACCEPTED");
        return new ResponseEntity<>(landlordRequests, HttpStatus.OK);
    }

    @PostMapping("/accept")
    public ResponseEntity<String> acceptFriendRequest(@RequestParam("requestId") int requestId) {
        try {
           frientRequestService.acceptFriendRequest(requestId);
            return new ResponseEntity<>("Friend request accepted", HttpStatus.OK);
        } catch (EntityNotFoundException | IllegalStateException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }


    @GetMapping("/sentTobroker/{brokerId}")
    public ResponseEntity<List<FriendRequesResDto>> getRequestsToBroker(@PathVariable int brokerId) {
        List<FriendRequesResDto> Requests = frientRequestService.getFriRequestsToBroker(brokerId);
//        List<FriendRequesResDto> landlordRequests = frientRequestService.getRequestsToBroker(tenantId);
        return new ResponseEntity<>(Requests, HttpStatus.OK);
    }
//    friendrequest/
    @GetMapping("/{Id}/{usertype}")
    public ResponseEntity<List<FriendRequesResDto>> getRequeststo(@PathVariable int Id,@PathVariable String usertype) {
//        List<FriendRequesResDto> Requests = frientRequestService.getFriRequestsToLandlord(Id);
        List<FriendRequesResDto> Requests = frientRequestService.getFriendRequests(Id,usertype);
//        List<FriendRequesResDto> landlordRequests = frientRequestService.getRequestsToBroker(tenantId);
        return new ResponseEntity<>(Requests, HttpStatus.OK);
    }

    @GetMapping("friends/{Id}/{usertype}")
    public ResponseEntity<List<FriendRequesResDto>> getFriends(@PathVariable int Id,@PathVariable String usertype) {
//
        List<FriendRequesResDto> Requests = frientRequestService.getFriends(Id,usertype,"ACCEPTED");

        return new ResponseEntity<>(Requests, HttpStatus.OK);
    }
}
