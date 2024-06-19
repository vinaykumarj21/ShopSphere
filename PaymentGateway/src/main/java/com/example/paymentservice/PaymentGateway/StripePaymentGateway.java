package com.example.paymentservice.PaymentGateway;

import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentLink;
import com.stripe.model.Price;
import com.stripe.param.PaymentLinkCreateParams;
import com.stripe.param.PriceCreateParams;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class StripePaymentGateway implements PaymentGateway{

    @Value("${stripe.key.api}")
    private String stripe_key;
    @Override
    public String generatePaymentLink(Long amount,String orderId,String name,String contact,String email) {
    try {
        Stripe.apiKey=stripe_key;
        Price price = getprice(amount);
        PaymentLinkCreateParams params =
                PaymentLinkCreateParams.builder()
                        .addLineItem(
                                PaymentLinkCreateParams.LineItem.builder()
                                        .setPrice(price.getId())
                                        .setQuantity(1L)
                                        .build()
                        )
                        .setAfterCompletion(PaymentLinkCreateParams.AfterCompletion.builder()
                                .setType(PaymentLinkCreateParams.AfterCompletion.Type.REDIRECT)
                                .setRedirect(PaymentLinkCreateParams.AfterCompletion.Redirect.builder()
                                        .setUrl("https://www.scaler.com")
                                        .build())
                                .build())
                        .build();

            PaymentLink paymentLink = PaymentLink.create(params);
            return paymentLink.getUrl();
        }
    catch (StripeException ex){
        throw new RuntimeException(ex);
        }
    }

    private Price getprice(Long amount){
        try {
            PriceCreateParams params =
                    PriceCreateParams.builder()
                            .setCurrency("USD")
                            .setUnitAmount(amount)
                            .setProductData(
                                    PriceCreateParams.ProductData.builder().setName("Gold Plan").build()
                            )
                            .build();

            Price price = Price.create(params);
            return price;
        }catch(StripeException ex) {
            throw new RuntimeException(ex);
        }
    }
}
