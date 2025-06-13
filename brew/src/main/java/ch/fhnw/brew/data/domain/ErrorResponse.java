package ch.fhnw.brew.data.domain;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ErrorResponse {
    private List<String> messages;

    public ErrorResponse(List<String> messages) {
        this.messages = messages;
    }

    public List<String> getMessages() {
        return messages;
    }
}
