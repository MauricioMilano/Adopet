package com.adopet.adopet.models.responses;

import com.adopet.adopet.models.Messages;
import com.adopet.adopet.models.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.util.ArrayList;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MessagesResponse {
    Long id;
    Date date;
    String content;
    UserResponse userTo;
    UserResponse userFrom;
    public MessagesResponse(Messages message){
        this.setId(message.getId());
        this.setDate(message.getDate());
        this.setContent(message.getContent());
        this.setUserTo(new UserResponse(message.getUserTo()));
        this.setUserFrom(new UserResponse(message.getUserFrom()));
    }
    public static ArrayList<MessagesResponse> getMessagesResponsesFromArrayOfMessages(ArrayList<Messages> messages){
        ArrayList<MessagesResponse> responses = new ArrayList<>();
        messages.forEach(message -> {
            responses.add(new MessagesResponse(message));
        } );
        return responses;
    }
    public static ArrayList<MessagesResponse> AppendArrayOfResponse(ArrayList<MessagesResponse> messagesResponses, ArrayList<Messages> messages){
        messages.forEach(message -> {
            messagesResponses.add(new MessagesResponse(message));
        } );
        return messagesResponses;
    }
}
