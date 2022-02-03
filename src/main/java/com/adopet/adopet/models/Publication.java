package com.adopet.adopet.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Publication {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;
    String description;
    String address;
    String zipcode;
    @OneToMany(
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<Pet> pets = new ArrayList<>();
    @OneToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    User userFrom;

}
