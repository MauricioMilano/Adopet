package com.adopet.adopet.controller;

import com.adopet.adopet.models.Publication;
import com.adopet.adopet.repositories.PublicationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@ResponseBody
public class PublicationController {
    @Autowired
    PublicationRepository repository;

    @GetMapping("/publications")
    public List<Publication> getAllPublications(){
        return repository.findAll();
    }

    @PostMapping("/publications")
    public Publication savePublication(@RequestBody Publication publication) {
        return repository.save(publication);
    }

    @DeleteMapping("/publications/{id}")
    public void deletePublication(@PathVariable Long id){
        repository.deleteById(id);
    }

    @PutMapping("/publications/{id}")
    public Publication updatePublication(@PathVariable Long id,@RequestBody Publication publication){
        Publication pb = repository.findById(id).get();
        pb.setAddress(publication.getAddress());
        pb.setDescription(publication.getDescription());
        pb.setZipcode(publication.getZipcode());
        return repository.save(pb);
    }

}