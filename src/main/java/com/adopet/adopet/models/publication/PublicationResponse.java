package com.adopet.adopet.models.publication;

import com.adopet.adopet.models.Publication;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PublicationResponse {
    Long id;
    String description;
    String address;
    String zipcode;
    UserResponse user;
    public PublicationResponse(Publication publication){
        this.setId(publication.getId());
        this.setAddress(publication.getAddress());
        this.setZipcode(publication.getZipcode());
        this.setDescription(publication.getDescription());
        UserResponse userResponse = new UserResponse();
        userResponse.setId(publication.getUserFrom().getId());
        userResponse.setUsername(publication.getUserFrom().getUsername());
        userResponse.setEmail(publication.getUserFrom().getEmail());
        userResponse.setRoles(publication.getUserFrom().getRoles());
        this.setUser(userResponse);
    }
}
