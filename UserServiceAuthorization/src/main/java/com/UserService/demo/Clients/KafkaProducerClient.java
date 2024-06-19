package com.UserService.demo.Clients;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class KafkaProducerClient {

    private KafkaTemplate<String , String>kafkaTemplate;
   //topic , msg -> both are Strings

    public KafkaProducerClient(KafkaTemplate<String , String>kafkaTemplate){
        this.kafkaTemplate=kafkaTemplate;
    }

    public void sendMessage(String topic ,String message){
        kafkaTemplate.send(topic,message);
    }
}
