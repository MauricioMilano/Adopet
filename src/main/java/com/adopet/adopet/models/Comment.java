package com.adopet.adopet.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Comment {
    @Id
    @GeneratedValue
    Long id;
    String content;
    @OneToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    User userFrom;
    @OneToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "publication_id", referencedColumnName = "id")
    Publication publication;
}
