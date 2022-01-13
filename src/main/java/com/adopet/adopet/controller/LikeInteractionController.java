package com.adopet.adopet.controller;

import com.adopet.adopet.models.LikeInteraction;
import com.adopet.adopet.models.Pet;
import com.adopet.adopet.models.Publication;
import com.adopet.adopet.models.User;
import com.adopet.adopet.models.publication.PublicationResponse;
import com.adopet.adopet.repositories.LikeRepository;
import com.adopet.adopet.repositories.PublicationRepository;
import com.adopet.adopet.repositories.UserRepository;
import com.adopet.adopet.security.services.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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
    public int getLikesFromPublication(@PathVariable Long publication_id){
        return this.getQtdLikes(publication_id);
    }

    @PostMapping("/like/{publication_id}")
    public int saveLike (@PathVariable Long publication_id) {
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
                  }else {
                      LikeInteraction like = new LikeInteraction();
                      like.setUserFrom(user.get());
                      like.setPublication(publication.get());
                      repository.save(like);
                  }
              }
            }
        } else {
            String user = principal.toString();
        }
        return getQtdLikes(publication_id);
    }
    private int getQtdLikes (Long publication_id) {
        Optional<List<LikeInteraction>> publication = repository.findAllByPublicationId(publication_id);
        if(publication.isPresent()){
            return publication.get().size();
        }else {
            return 0;
        }
    }
}
