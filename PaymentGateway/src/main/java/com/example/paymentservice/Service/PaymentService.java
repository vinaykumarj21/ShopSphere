package com.example.paymentservice.Service;

import com.example.paymentservice.PaymentGateway.PaymentGateway;
import com.example.paymentservice.PaymentGateway.PaymentGatewayStrategyChooser;
import com.razorpay.RazorpayException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaymentService implements PaymentServices{

    @Autowired
    private PaymentGatewayStrategyChooser paymentGatewayStrategyChooser;

    @Override
    public String initiatePayment(long amount,String orderId,String name,String contact,String email) throws RazorpayException {
        PaymentGateway paymentGateway= paymentGatewayStrategyChooser.getBestPaymentGateway();
        return paymentGateway.generatePaymentLink(amount,orderId,name,contact,email);
    }

}
