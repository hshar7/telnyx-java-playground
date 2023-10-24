package dev.nayzek.telnyxsdkplaygroundsb2.service;

import com.telnyx.sdk.ApiException;
import com.telnyx.sdk.api.MessagesApi;
import com.telnyx.sdk.model.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@org.springframework.stereotype.Service
public class TelnyxMessagingService {

    @Autowired
    MessagesApi messagesApi;

    public void sendSMS(String from, String to, String message, String messagingProfileId) {
        CreateMessageRequest createMessageRequest = new CreateMessageRequest()
                .from(from)
                .to(to)
                .text(message)
                .messagingProfileId(messagingProfileId)
                .type(CreateMessageRequest.TypeEnum.SMS);

        try {
            MessageResponse result = messagesApi.createMessage(createMessageRequest);
            System.out.println(result);
        } catch (ApiException e) {
            System.err.println("Exception when calling MessagesApi#createMessage");
            System.err.println("Status code: " + e.getCode());
            System.err.println("Reason: " + e.getResponseBody());
            System.err.println("Response headers: " + e.getResponseHeaders());
            e.printStackTrace();
        }
    }

    public void sendMMS(String from, String to, String message, List<String> mediaUrls, String subject, String messagingProfileId) {
        CreateMessageRequest createMessageRequest = new CreateMessageRequest()
                .from(from)
                .to(to)
                .text(message)
                .mediaUrls(mediaUrls)
                .messagingProfileId(messagingProfileId)
                .subject(subject)
                .type(CreateMessageRequest.TypeEnum.MMS);

        try {
            MessageResponse result = messagesApi.createMessage(createMessageRequest);
            System.out.println(result);
        } catch (ApiException e) {
            System.err.println("Exception when calling MessagesApi#createMessage");
            System.err.println("Status code: " + e.getCode());
            System.err.println("Reason: " + e.getResponseBody());
            System.err.println("Response headers: " + e.getResponseHeaders());
            e.printStackTrace();
        }
    }
}
