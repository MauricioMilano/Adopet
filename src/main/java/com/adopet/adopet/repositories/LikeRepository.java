package com.adopet.adopet.repositories;

import com.adopet.adopet.models.Like;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LikeRepository extends JpaRepository<Like, Long> {
}
