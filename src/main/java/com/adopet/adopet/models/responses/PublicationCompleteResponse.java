package com.adopet.adopet.models.responses;

import com.adopet.adopet.models.Publication;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PublicationCompleteResponse extends PublicationResponse {
    boolean liked;
    List<CommentResponse> comments;
    public PublicationCompleteResponse(Publication publication){
        super(publication);
    }
}
