package com.example.SMS_Service.Utils;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

public class SmsUtil {
    /**
     * Utility method to send SMS
     *
     * @param accountSid Twilio account SID
     * @param authToken Twilio authentication token
     * @param to Recipient phone number
     * @param from Sender phone number (Twilio number)
     * @param body Message content
     */
    public static void sendSms(String accountSid, String authToken, String to, String from, String body) {
        try {
            Twilio.init(accountSid, authToken);
            Message message = Message.creator(
                    new PhoneNumber(to),
                    new PhoneNumber(from),
                    body
            ).create();

            System.out.println("SMS Sent Successfully! SID: " + message.getSid());
        } catch (Exception e) {
            System.out.println("Failed to send SMS");
            e.printStackTrace();
        }
    }
}
