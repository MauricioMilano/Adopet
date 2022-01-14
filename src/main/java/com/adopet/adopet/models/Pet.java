package com.adopet.adopet.models;

import com.adopet.adopet.models.publication.PetRequest;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Pet {
    @Id
    @GeneratedValue
    Long id;
    String name;
    @Lob
    @Basic(fetch = FetchType.LAZY)
    @Column(name = "pics", columnDefinition = "LONGBLOB")
    byte[]  pics;
    String age;
    public Pet (PetRequest petResponse){
        String encodedImg = petResponse.getPics().split(",")[1];
        this.setPics(Base64.getDecoder().decode(encodedImg.getBytes(StandardCharsets.UTF_8)));
        this.setAge(petResponse.getAge());
        this.setId(petResponse.getId());
        this.setName(petResponse.getName());
    }

}
