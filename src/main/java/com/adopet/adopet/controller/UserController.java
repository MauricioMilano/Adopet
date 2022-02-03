package com.adopet.adopet.controller;

import com.adopet.adopet.models.User;
import com.adopet.adopet.models.responses.UserResponse;
import com.adopet.adopet.repositories.UserRepository;
import com.adopet.adopet.security.services.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@ResponseBody
public class UserController {
    @Autowired
    UserRepository repository;
    @GetMapping("/user")
    @PreAuthorize("hasRole('ADMIN')")
    public List<User> getAllUsers(){
        return repository.findAll();
    }

    @GetMapping("/profile")
    public UserResponse getMyprofile(){
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Optional<User> user = repository.findById(((UserDetailsImpl)principal).getId());
        return new UserResponse(user.get());
    }

}
