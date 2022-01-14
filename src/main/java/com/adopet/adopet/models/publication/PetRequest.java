package com.adopet.adopet.models.publication;

import com.adopet.adopet.models.Pet;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PetRequest {
    @Id
    @GeneratedValue
    Long id;
    String name;
    String pics;
    String age;
    public PetRequest(Pet pet){
        this.setAge(pet.getAge());

    }
}
