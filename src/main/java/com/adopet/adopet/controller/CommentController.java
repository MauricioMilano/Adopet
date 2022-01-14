package com.adopet.adopet.controller;

import com.adopet.adopet.models.Comment;
import com.adopet.adopet.models.Publication;
import com.adopet.adopet.models.User;
import com.adopet.adopet.models.auth.MessageResponse;
import com.adopet.adopet.models.publication.CommentResponse;
import com.adopet.adopet.repositories.CommentRepository;
import com.adopet.adopet.repositories.PublicationRepository;
import com.adopet.adopet.repositories.UserRepository;
import com.adopet.adopet.security.services.UserDetailsImpl;
import org.aspectj.bridge.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@ResponseBody
public class CommentController {
    @Autowired
    CommentRepository repository;

    @Autowired
    PublicationRepository publicationRepository;

    @Autowired
    UserRepository userRepository;

    @GetMapping("/comment/{publication_id}")
    public List<CommentResponse> getAllComments(@PathVariable Long publication_id){
        Optional<Publication> publication = publicationRepository.findById(publication_id);
        List<Comment> comments = repository.findAllByPublication(publication.get());
        List<CommentResponse> commentResponses = new ArrayList<>();
        comments.forEach(comment -> {
            commentResponses.add(new CommentResponse(comment));
        });
        return commentResponses;
    }

    @PostMapping("/comment/{publication_id}")
    public CommentResponse postComment(@PathVariable Long publication_id, @RequestBody MessageResponse message){
        Comment comment = new Comment();
        comment.setContent(message.getMessage());
        // Usuario da requisição
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Long id = ((UserDetailsImpl)principal).getId();
        Optional<User> user = userRepository.findById(id);
        // fim do usuario
        comment.setUserFrom(user.get());
        Optional<Publication> publication = publicationRepository.findById(publication_id);
        comment.setPublication(publication.get());
        repository.save(comment);
        return new CommentResponse(comment);
    }
}
