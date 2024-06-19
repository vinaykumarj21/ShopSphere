package com.example.paymentservice.PaymentGateway;

import com.razorpay.RazorpayException;

public interface PaymentGateway {
    String generatePaymentLink(Long amount,String orderId,String name,String contact,String email) throws RazorpayException;
}
