package com.adopet.adopet.repositories;

import com.adopet.adopet.models.LikeInteraction;
import com.adopet.adopet.models.Messages;
import com.adopet.adopet.models.Publication;
import com.adopet.adopet.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.ArrayList;
import java.util.Optional;

public interface MessagesRepository extends JpaRepository<Messages, Long> {
    public ArrayList<Messages> findByUserFromAndUserTo(User userFrom, User userTo);

    @Query("SELECT DISTINCT userFrom FROM Messages WHERE user_from = :user OR user_to = :user")
    ArrayList<User> selectUserFromMessages(@Param("user") Long user);
    @Query("SELECT DISTINCT userTo FROM Messages WHERE user_from = :user OR user_to = :user")
    ArrayList<User> selectUserToMessages(@Param("user") Long user);
}
