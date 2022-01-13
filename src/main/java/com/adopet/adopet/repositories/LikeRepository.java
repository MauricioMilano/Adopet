package com.adopet.adopet.repositories;

import com.adopet.adopet.models.LikeInteraction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LikeRepository extends JpaRepository<LikeInteraction, Long> {
}
