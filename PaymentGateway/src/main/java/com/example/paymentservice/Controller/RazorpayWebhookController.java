package com.example.paymentservice.Controller;


import com.stripe.model.Event;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/razorpayWebHook")
public class RazorpayWebhookController {

    @PostMapping
    public void receiveWebhookEvents(Event event){
        System.out.println("Done");
    }

}
