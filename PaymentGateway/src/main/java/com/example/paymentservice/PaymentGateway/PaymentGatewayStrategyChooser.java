package com.example.paymentservice.PaymentGateway;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class PaymentGatewayStrategyChooser {

    @Autowired
    private RazorpayPaymentGateway razorpayPaymentGateway;

    @Autowired
    private StripePaymentGateway stripePaymentGateway;

    public PaymentGateway getBestPaymentGateway(){
        //Randomly choosing Payment Gateway
        String[] arr={"razorpayPaymentGateway","stripePaymentGateway"};

        //Randomly selecting the PaymentGateway
        Random r=new Random();
        int randomNumber=r.nextInt(arr.length);
        String s=arr[randomNumber];
        //if(s.equals("stripePaymentGateway"))
            return razorpayPaymentGateway;

       // return stripePaymentGateway;
    }
}
