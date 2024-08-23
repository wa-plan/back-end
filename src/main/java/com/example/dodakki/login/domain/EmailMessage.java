package com.example.dodakki.login.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EmailMessage {

    private String to;
    private String subject;
    private String message;

    public EmailMessage(String to, String subject) {
        this.to = to;
        this.subject = subject;
    }
}
