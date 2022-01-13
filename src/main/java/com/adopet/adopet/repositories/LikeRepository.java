package com.adopet.adopet.repositories;

import com.adopet.adopet.models.LikeInteraction;
import com.adopet.adopet.models.Publication;
import com.adopet.adopet.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LikeRepository extends JpaRepository<LikeInteraction, Long> {
//    @Query("SELECT t FROM LikeInteraction t where t.user_id = :UserId AND t.publication_id = :PublicationId" )
    public Optional<LikeInteraction> findByUserFromAndPublication(User UserId, Publication PublicationId);
//    @Query("SELECT * FROM LikeInteraction t where t.publication_id = :publicationId")
    public Optional<List<LikeInteraction>> findAllByPublicationId(Long publicationId);
//     List<LikeInteraction> findByPublicationAndUser(Long publication, Long user );
}