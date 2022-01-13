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
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    UserA userFrom;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "publication_id", referencedColumnName = "id")
    Publication publication;
}
