package dev.nayzek.telnyxsdkplaygroundsb2.controller;

import dev.nayzek.telnyxsdkplaygroundsb2.controller.data.MessageRequest;
import dev.nayzek.telnyxsdkplaygroundsb2.service.TelnyxMessagingService;
import dev.nayzek.telnyxsdkplaygroundsb2.service.TelnyxVoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
public class TelnyxController {

    @Autowired
    private Environment env;

    @Autowired
    TelnyxVoiceService telnyxVoiceService;

    @Autowired
    TelnyxMessagingService telnyxMessagingService;

    @PostMapping(value = "/api/dial")
    Map<String, String> dialDemo(@RequestParam String[] numbers) {
        telnyxVoiceService.dial(
                env.getProperty("caller.number"),
                Arrays.stream(numbers).toList(),
                env.getProperty("whisper.url"),
                true
        );
        Map<String, String> response = new HashMap<>();
        response.put("success", "true");
        return response;
    }

    @PostMapping(value = "/api/sms")
    Map<String, String> smsDemo(@RequestBody MessageRequest request) {
        telnyxMessagingService.sendSMS(
                env.getProperty("caller.number"),
                request.getNumber(),
                request.getMessage(),
                env.getProperty("messaging.profile")
        );
        Map<String, String> response = new HashMap<>();
        response.put("success", "true");
        return response;
    }

    @PostMapping(value = "/api/mms")
    Map<String, String> mmsDemo(@RequestBody MessageRequest request) {
        telnyxMessagingService.sendMMS(
                env.getProperty("caller.number"),
                request.getNumber(),
                request.getMessage(),
                new ArrayList<>(Collections.singleton(env.getProperty("mms.media"))),
                "MY MMS SUBJECT",
                env.getProperty("messaging.profile")
        );
        Map<String, String> response = new HashMap<>();
        response.put("success", "true");
        return response;
    }
}
