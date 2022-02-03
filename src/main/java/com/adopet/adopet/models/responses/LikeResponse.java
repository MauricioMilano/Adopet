package com.adopet.adopet.models.responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LikeResponse {
    int qtdLikes;
    Boolean youLiked;
}
