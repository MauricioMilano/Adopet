package com.adopet.adopet.repositories;

import com.adopet.adopet.models.Publication;
import com.adopet.adopet.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.ArrayList;

public interface PublicationRepository extends JpaRepository<Publication, Long> {
    public ArrayList<Publication> findAllByUserFrom(User user);
}
