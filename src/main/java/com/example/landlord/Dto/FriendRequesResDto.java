package com.example.landlord.Dto;

import com.example.landlord.actoinresponse.ApiResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@NoArgsConstructor
@AllArgsConstructor
@Data
public class FriendRequesResDto {




        private int id;
        private int requestId;
        private String name;
        private String email;
        private String profile;

//private ApiResponse actionresponse;

}
