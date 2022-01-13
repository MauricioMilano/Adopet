package com.adopet.adopet.repositories;

import com.adopet.adopet.models.Pet;
import com.adopet.adopet.models.Publication;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PetRepository extends JpaRepository<Pet, Long> {
}

