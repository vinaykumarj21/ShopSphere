package com.example.EmailService.DTOs;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SendMessageDTO {
    private String to;
    private String from;
    private String subject;
    private String body;
}
