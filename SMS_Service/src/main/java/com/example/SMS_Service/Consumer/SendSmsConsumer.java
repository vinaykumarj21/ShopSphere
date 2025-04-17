package com.example.SMS_Service.Consumer;

import com.example.SMS_Service.DTOs.SendSmsDTO;
import com.example.SMS_Service.Utils.SmsUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class SendSmsConsumer {
    @Autowired
    private ObjectMapper objectMapper;

    @Value("${twilio.account.sid}")
    private String twilioAccountSid;

    @Value("${twilio.auth.token}")
    private String twilioAuthToken;

    @Value("${twilio.phone.number}")
    private String twilioPhoneNumber;

    @KafkaListener(topics = "sendSms", groupId = "smsService") // listens the events with topic=sendSms
    public void handleSendSms(String message) {
        try {
            SendSmsDTO sendSmsDTO = objectMapper.readValue(message, SendSmsDTO.class);
            SmsUtil.sendSms(
                    twilioAccountSid,
                    twilioAuthToken,
                    sendSmsDTO.getTo(),
                    twilioPhoneNumber,
                    sendSmsDTO.getBody()
            );
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}