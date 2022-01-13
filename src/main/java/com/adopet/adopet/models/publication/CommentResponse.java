package com.adopet.adopet.models.publication;

import com.adopet.adopet.models.Comment;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CommentResponse {
    Long id;
    String content;
//    UserResponse user;
//    PublicationResponse publication;
    public CommentResponse(Comment comment){
//        this.setUser(new UserResponse(comment.getUserFrom()));
//        this.setPublication(new PublicationResponse(comment.getPublication()));
        this.setId(comment.getId());
        this.setContent(comment.getContent());
    }
}
