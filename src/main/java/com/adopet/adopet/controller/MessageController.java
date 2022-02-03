package com.adopet.adopet.controller;

import com.adopet.adopet.models.LikeInteraction;
import com.adopet.adopet.models.Messages;
import com.adopet.adopet.models.Publication;
import com.adopet.adopet.models.User;
import com.adopet.adopet.models.requests.MessageRequest;
import com.adopet.adopet.models.responses.LikeResponse;
import com.adopet.adopet.models.responses.MessagesResponse;
import com.adopet.adopet.models.responses.UserResponse;
import com.adopet.adopet.repositories.LikeRepository;
import com.adopet.adopet.repositories.MessagesRepository;
import com.adopet.adopet.repositories.PublicationRepository;
import com.adopet.adopet.repositories.UserRepository;
import com.adopet.adopet.security.services.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Optional;
import java.util.stream.Stream;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@ResponseBody
public class MessageController {
    @Autowired
    MessagesRepository repository;
    @Autowired
    UserRepository userRepository;

    @GetMapping("/chat/{userTo_id}")
    public ArrayList<MessagesResponse> getChatMessage(@PathVariable Long userTo_id){
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Optional<User> user = userRepository.findById(((UserDetailsImpl)principal).getId());
        Optional<User> to = userRepository.findById(userTo_id);
//        if (user.isPresent() &&  to.isPresent()){
        ArrayList<Messages> userFrom = repository.findByUserFromAndUserTo(user.get(), to.get());
        ArrayList<Messages> userTo = repository.findByUserFromAndUserTo(to.get(), user.get());

        ArrayList<MessagesResponse> responses = MessagesResponse.getMessagesResponsesFromArrayOfMessages(userFrom);
        responses = MessagesResponse.AppendArrayOfResponse(responses, userTo);
        return responses;
//        }
    }

    @GetMapping("/myChats")
    public Object[] getMyConversations(){
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Optional<User> user = userRepository.findById(((UserDetailsImpl)principal).getId());
        ArrayList<User> usersFrom = repository.selectUserFromMessages(user.get().getId());
        ArrayList<User> usersTo = repository.selectUserToMessages(user.get().getId());
        ArrayList<UserResponse> userResponses = UserResponse.getUserResponsesFromArrayOfUsers(usersFrom);
        userResponses = UserResponse.AppendArrayOfUsers(userResponses, usersTo);

        Stream<UserResponse> stream = userResponses.stream().filter(userResponse -> {return userResponse.getId()!= user.get().getId();});
        Object[] userResponses1 = stream.toArray();
        return userResponses1;
    }
    @PostMapping("/chat/{userTo_id}")
    public MessagesResponse sendChatMessage(@PathVariable Long userTo_id, @RequestBody MessageRequest message){
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Optional<User> user = userRepository.findById(((UserDetailsImpl)principal).getId());
        Optional<User> to = userRepository.findById(userTo_id);
        Messages messages = new Messages();
//        if (user.isPresent() && to.isPresent() ) {
        messages.setContent(message.getContent());
        messages.setUserFrom(user.get());
        messages.setUserTo(to.get());
        messages.setDate(new Date(System.currentTimeMillis()));
        repository.save(messages);

//        }
        return new MessagesResponse(messages);
    }


}
