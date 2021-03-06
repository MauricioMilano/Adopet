package com.adopet.adopet.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Messages {
    @Id
    @GeneratedValue
    Long id;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_from", referencedColumnName = "id")
    User userFrom;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_to", referencedColumnName = "id")
    User userTo;
    Date date;
    String content;
}
