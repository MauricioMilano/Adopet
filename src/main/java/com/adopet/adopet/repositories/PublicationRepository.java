package com.adopet.adopet.repositories;

import com.adopet.adopet.models.Publication;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PublicationRepository extends JpaRepository<Publication, Long> {
}
