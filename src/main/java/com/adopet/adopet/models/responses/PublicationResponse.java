package com.adopet.adopet.models.responses;

import com.adopet.adopet.models.Messages;
import com.adopet.adopet.models.Pet;
import com.adopet.adopet.models.Publication;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PublicationResponse {
    Long id;
    String description;
    String address;
    String zipcode;
    UserResponse user;
    private List<Pet> pets;
    public PublicationResponse(Publication publication){
        this.setId(publication.getId());
        this.setAddress(publication.getAddress());
        this.setZipcode(publication.getZipcode());
        this.setDescription(publication.getDescription());
        UserResponse userResponse = new UserResponse(publication.getUserFrom());
        this.setUser(userResponse);
        this.setPets(publication.getPets());
    }
    public static ArrayList<PublicationResponse> getPublicationResponsesFromArrayOfPublications(ArrayList<Publication> publications){
        ArrayList<PublicationResponse> responses = new ArrayList<>();
        publications.forEach(publication -> {
            responses.add(new PublicationResponse(publication));
        } );
        return responses;
    }
}
