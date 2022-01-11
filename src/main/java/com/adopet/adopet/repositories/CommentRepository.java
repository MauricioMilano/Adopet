package com.adopet.adopet.repositories;

import com.adopet.adopet.models.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}
