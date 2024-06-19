package com.example.paymentservice.DTOs;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class InitiatePaymentDTO {
    private long amount;
    private String orderId;
    private String name;
    private String contact;
    private String email;
}
