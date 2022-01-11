package com.adopet.adopet.repositories;

import com.adopet.adopet.models.Messages;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessagesRepository extends JpaRepository<Messages, Long> {
}
