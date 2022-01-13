package com.adopet.adopet.repositories;

import com.adopet.adopet.models.Comment;
import com.adopet.adopet.models.Publication;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    public List<Comment> findAllByPublication(Publication publicationId);
}
