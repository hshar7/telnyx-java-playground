package dev.nayzek.telnyxsdkplaygroundsb2.controller;

import dev.nayzek.telnyxsdkplaygroundsb2.controller.data.TelnyxMessageEvent;
import dev.nayzek.telnyxsdkplaygroundsb2.service.TelnyxVoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@RestController
public class TelnyxMessagesWebhookController {

    @Autowired
    private TelnyxVoiceService telnyxVoiceService;

    @PostMapping(value = "/api/telnyxmessages")
    Map<String, String> dialDemo(@RequestBody TelnyxMessageEvent telnyxMessageEvent) {

        System.out.println(telnyxMessageEvent);

        if (Objects.equals(telnyxMessageEvent.getData().getEvent_type(), "message.received")) {
            System.out.println("Message Received from " + telnyxMessageEvent.getData().getPayload().getFrom().getPhone_number());
            System.out.println("Message Received content: " + telnyxMessageEvent.getData().getPayload().getText());
        } else if (Objects.equals(telnyxMessageEvent.getData().getEvent_type(), "message.finalized")) {
            System.out.println("Message Finalized from " + telnyxMessageEvent.getData().getPayload().getFrom().getPhone_number());
            System.out.println("Message Finalized content: " + telnyxMessageEvent.getData().getPayload().getText());
        }

        Map<String, String> response = new HashMap<>();
        response.put("success", "true");
        return response;
    }
}
