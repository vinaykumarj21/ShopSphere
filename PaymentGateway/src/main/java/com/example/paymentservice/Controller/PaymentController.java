package com.example.paymentservice.Controller;

import com.example.paymentservice.DTOs.InitiatePaymentDTO;
import com.example.paymentservice.Service.PaymentServices;
import com.razorpay.RazorpayException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/payments")
public class PaymentController {

    @Autowired
    private PaymentServices paymentService;

    //return paymentLink of PG(Payment Gateway)
    @PostMapping()
    public String initiatePayment(@RequestBody InitiatePaymentDTO initiatePaymentDTO) throws RazorpayException {
        return paymentService.initiatePayment(initiatePaymentDTO.getAmount(),initiatePaymentDTO.getOrderId(),
                initiatePaymentDTO.getName(),initiatePaymentDTO.getContact(),initiatePaymentDTO.getEmail());

    }


}
