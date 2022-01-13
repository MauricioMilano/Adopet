package com.adopet.adopet.repositories;

import java.util.Optional;

import com.adopet.adopet.models.ERole;
import com.adopet.adopet.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(ERole name);
}