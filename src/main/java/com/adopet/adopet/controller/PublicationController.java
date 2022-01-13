package com.adopet.adopet.controller;

import com.adopet.adopet.models.Publication;
import com.adopet.adopet.models.User;
import com.adopet.adopet.models.auth.MessageResponse;
import com.adopet.adopet.models.publication.PublicationResponse;
import com.adopet.adopet.models.publication.UserResponse;
import com.adopet.adopet.repositories.PublicationRepository;
import com.adopet.adopet.repositories.UserRepository;
import com.adopet.adopet.security.services.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@ResponseBody
public class PublicationController {
    @Autowired
    PublicationRepository repository;
    @Autowired
    UserRepository userRepository;
    @GetMapping("/publications")
    public List<PublicationResponse> getAllPublications(){
        List<Publication> pub = repository.findAll();
        List<PublicationResponse> pubResponse = new ArrayList<>();
        pub.forEach(publication -> {
            pubResponse.add(new PublicationResponse(publication));
        });
        return pubResponse;
    }

    @PostMapping("/publications")
    public PublicationResponse savePublication(@RequestBody Publication publication) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (principal instanceof UserDetailsImpl) {
            Long id = ((UserDetailsImpl)principal).getId();
            Optional<User> user = userRepository.findById(id);
            if (user.isPresent()) {
                publication.setUserFrom(user.get());
            }
        } else {
            String user = principal.toString();
        }
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