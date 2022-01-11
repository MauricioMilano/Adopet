package com.adopet.adopet.repositories;

import com.adopet.adopet.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
