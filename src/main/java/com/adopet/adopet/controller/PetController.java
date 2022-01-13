package com.adopet.adopet.controller;


import com.adopet.adopet.models.Pet;
import com.adopet.adopet.models.Publication;
import com.adopet.adopet.repositories.PetRepository;
import com.adopet.adopet.repositories.PublicationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@ResponseBody
public class PetController {
    @Autowired
    PetRepository repository;

    @GetMapping("/pet")
    public List<Pet> getAllPets() { return repository.findAll();}

    @PostMapping("/pet")
    public Pet savePet (@RequestBody Pet pet) {
        return repository.save(pet);
    }

    @DeleteMapping("/pet/{id}")
    public void deletePet(@PathVariable Long id){
        repository.deleteById(id);
    }

    @PutMapping("/pet/{id}")
    public Pet updatePet(@PathVariable Long id, @RequestBody Pet pet){
        Pet pt = repository.findById(id).get();
        pt.setAge(pet.getAge());
        pt.setName(pet.getName());
        pt.setPics(pet.getPics());
        return repository.save(pt);
    }

}
