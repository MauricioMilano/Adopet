package com.adopet.adopet.models.requests;

import com.adopet.adopet.models.responses.UserResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PublicationRequest {
    Long id;
    String description;
    String address;
    String zipcode;
    UserResponse user;
    private List<PetRequest> pets;
}
