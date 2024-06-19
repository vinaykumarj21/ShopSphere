package com.example.paymentservice.Controller;

import com.stripe.model.Event;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/stripeWebHook")
public class StripeWebhookController {

    @PostMapping
    public void receiveWebHookEvents(Event event){
        if(event.equals("payment_link.created"))
            System.out.println("payment_link.created");
        else
            System.out.println("Completed");
    }

}
