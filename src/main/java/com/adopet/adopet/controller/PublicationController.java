package com.adopet.adopet.controller;

import com.adopet.adopet.models.*;
import com.adopet.adopet.models.auth.MessageResponse;
import com.adopet.adopet.models.publication.*;
import com.adopet.adopet.repositories.CommentRepository;
import com.adopet.adopet.repositories.LikeRepository;
import com.adopet.adopet.repositories.PublicationRepository;
import com.adopet.adopet.repositories.UserRepository;
import com.adopet.adopet.security.services.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@ResponseBody
public class PublicationController {
    @Autowired
    PublicationRepository repository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    CommentRepository commentRepository;
    @Autowired
    LikeRepository likeRepository;
    @GetMapping("/publications")
    public List<PublicationCompleteResponse> getAllPublications(){
        List<Publication> pub = repository.findAll();
        List<PublicationCompleteResponse> pubResponse = new ArrayList<>();
        pub.forEach(publication -> {
            PublicationCompleteResponse publicationCompleteResponse = new PublicationCompleteResponse(publication);
            List<Comment> comments = commentRepository.findAllByPublication(publication);
            List<CommentResponse> commentsResponses = new ArrayList<>();
            comments.forEach(comment -> {
                commentsResponses.add(new CommentResponse(comment));
            });
            publicationCompleteResponse.setComments(commentsResponses);
            Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            Long id = ((UserDetailsImpl)principal).getId();
            Optional<User> user = userRepository.findById(id);
            Optional<LikeInteraction> like = likeRepository.findByUserFromAndPublication(user.get(),publication);
            if(like.isPresent()){
                publicationCompleteResponse.setLiked(true);
            }else {
                publicationCompleteResponse.setLiked(false);
            }
            pubResponse.add(publicationCompleteResponse);
        });
        return pubResponse;
    }

    @PostMapping("/publications")
    public PublicationResponse savePublication(@RequestBody PublicationRequest publicationRequest) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Publication publication = new Publication();
        if (principal instanceof UserDetailsImpl) {
            Long id = ((UserDetailsImpl)principal).getId();
            Optional<User> user = userRepository.findById(id);
            if (user.isPresent()) {
                publication.setUserFrom(user.get());
            }
        } else {
            String user = principal.toString();
        }
        publication.setAddress(publicationRequest.getAddress());
        publication.setDescription(publicationRequest.getDescription());
        publication.setZipcode(publicationRequest.getZipcode());
        List<Pet> pets = new ArrayList<>();
        publicationRequest.getPets().forEach(pet -> {
            pets.add(new Pet(pet));
        });
       publication.setPets(pets);
       repository.save(publication);
       PublicationResponse publicationResponse = new PublicationResponse(publication);
       return publicationResponse;
    }

    @DeleteMapping("/publications/{id}")
    public void deletePublication(@PathVariable Long id){
        repository.deleteById(id);
    }

    @PutMapping("/publications/{id}")
    public PublicationResponse updatePublication(@PathVariable Long id,@RequestBody Publication publication){
        Publication pb = repository.findById(id).get();
        pb.setAddress(publication.getAddress());
        pb.setDescription(publication.getDescription());
        pb.setZipcode(publication.getZipcode());
        PublicationResponse publicationResponse = new PublicationResponse(repository.save(pb));
        return publicationResponse;
    }

}