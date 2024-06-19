package com.example.paymentservice.Service;

import com.razorpay.RazorpayException;

public interface PaymentServices {
    String initiatePayment(long amount,String orderId,String name,String contact,String email) throws RazorpayException;
}
