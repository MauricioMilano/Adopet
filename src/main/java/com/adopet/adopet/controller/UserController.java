package com.adopet.adopet.controller;

import com.adopet.adopet.models.User;
import com.adopet.adopet.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

//    @PostMapping("/user")
//    public User saveUser(@RequestBody User user){
//        return repository.save(user);
//    }

}
