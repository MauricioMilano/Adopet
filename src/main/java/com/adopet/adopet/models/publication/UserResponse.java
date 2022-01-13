package com.adopet.adopet.models.publication;

import com.adopet.adopet.models.Role;
import com.adopet.adopet.models.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.util.Set;
@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserResponse {
    private Long id;
    private String username;
    private String email;
//    private Set<Role> roles;

    public UserResponse (User user){
        this.setId(user.getId());
        this.setUsername(user.getUsername());
        this.setEmail(user.getEmail());
//        this.setRoles(user.getRoles());
    }
}