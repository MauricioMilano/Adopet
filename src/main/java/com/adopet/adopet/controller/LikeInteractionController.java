package com.adopet.adopet.controller;

import com.adopet.adopet.models.LikeInteraction;
import com.adopet.adopet.models.Publication;
import com.adopet.adopet.models.User;
import com.adopet.adopet.models.responses.LikeResponse;
import com.adopet.adopet.repositories.LikeRepository;
import com.adopet.adopet.repositories.PublicationRepository;
import com.adopet.adopet.repositories.UserRepository;
import com.adopet.adopet.security.services.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@ResponseBody
public class LikeInteractionController {
    @Autowired
    LikeRepository repository;
    @Autowired
    PublicationRepository publicationRepository;
    @Autowired
    UserRepository userRepository;
    @GetMapping("/like/{publication_id}")
    public LikeResponse getLikesFromPublication(@PathVariable Long publication_id){
        LikeResponse response = new LikeResponse();
        response.setQtdLikes(getQtdLikes(publication_id));
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Optional<User> user = userRepository.findById(((UserDetailsImpl)principal).getId());
        Optional<Publication> publication = publicationRepository.findById(publication_id);

        if (user.isPresent() && publication.isPresent() ) {
           Optional<LikeInteraction> like =  repository.findByUserFromAndPublication(user.get(),publication.get());
           if(like.isPresent()){
               response.setYouLiked(true);
           }else{
               response.setYouLiked(false);
           }
        }
        response.setQtdLikes(getQtdLikes(publication_id));
        return response;
    }

    @PostMapping("/like/{publication_id}")
    public LikeResponse saveLike (@PathVariable Long publication_id) {
        LikeResponse response =  new LikeResponse();
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetailsImpl) {
            Long id = ((UserDetailsImpl)principal).getId();
            Optional<User> user = userRepository.findById(id);
            if (user.isPresent()) {
              Optional<Publication> publication = publicationRepository.findById(publication_id);
              if (publication.isPresent()){
                Optional<LikeInteraction> Like = repository.findByUserFromAndPublication(user.get(), publication.get());
                  if(Like.isPresent()){
                      repository.delete(Like.get());
                      response.setYouLiked(false);
                  }else {
                      LikeInteraction like = new LikeInteraction();
                      like.setUserFrom(user.get());
                      like.setPublication(publication.get());
                      repository.save(like);
                      response.setYouLiked(true);
                  }
              }
            }
        } else {
            String user = principal.toString();
        }

        response.setQtdLikes(getQtdLikes(publication_id));
        return response;
    }
    private int getQtdLikes (Long publication_id) {
        Optional<List<LikeInteraction>> publication = repository.findAllByPublicationId(publication_id);
        if(publication.isPresent()){
            int size = publication.get().size();

            return size;
        }else {
            return 0;
        }
    }
    private Boolean isLiked(User user, Publication publication){
        Optional<LikeInteraction> Like = repository.findByUserFromAndPublication(user, publication);
        if(Like.isPresent()){
            return true;
        }else {
            return false;
        }
    }
}
