package com.adopet.adopet.models.responses;

import com.adopet.adopet.models.Publication;
import com.adopet.adopet.models.Role;
import com.adopet.adopet.models.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.util.ArrayList;
import java.util.Set;
@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserResponse {
    private Long id;
    private String username;
    private byte[] picture;
    private String email;
    private Set<Role> roles;

    public UserResponse (User user){
        this.setId(user.getId());
        this.setUsername(user.getUsername());
        this.setPicture(user.getPicture());
        this.setEmail(user.getEmail());
        this.setRoles(user.getRoles());
    }
    public static ArrayList<UserResponse> getUserResponsesFromArrayOfUsers(ArrayList<User> users){
        ArrayList<UserResponse> responses = new ArrayList<>();
        users.forEach(user -> {
            responses.add(new UserResponse(user));
        } );
        return responses;
    }
    public static ArrayList<UserResponse> AppendArrayOfUsers(ArrayList<UserResponse> userResponses, ArrayList<User> users){
        users.forEach(user -> {
            userResponses.add(new UserResponse(user));
        } );
        return userResponses;
    }
}