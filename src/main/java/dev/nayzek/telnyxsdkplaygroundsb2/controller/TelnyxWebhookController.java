package dev.nayzek.telnyxsdkplaygroundsb2.controller;

import dev.nayzek.telnyxsdkplaygroundsb2.controller.data.TelnyxEvent;
import dev.nayzek.telnyxsdkplaygroundsb2.service.TelnyxVoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@RestController
public class TelnyxWebhookController {

    @Autowired
    private Environment env;

    @Autowired
    private TelnyxVoiceService telnyxVoiceService;

    @PostMapping(value = "/api/telnyx")
    Map<String, String> dialDemo(@RequestBody TelnyxEvent telnyxEvent) {

        System.out.println(telnyxEvent);

        if (Objects.equals(telnyxEvent.getData().getEvent_type(), "call.initiated")) {
            telnyxVoiceService.answerCall(telnyxEvent.getData().getPayload().getCall_control_id());
        } else if (Objects.equals(telnyxEvent.getData().getEvent_type(), "call.answered")) {
            telnyxVoiceService.createGatherObj(
                    telnyxEvent.getData().getPayload().getCall_control_id(),
                    env.getProperty("whisper.url"),
                    2,
                    60000
            );
        } else if (Objects.equals(telnyxEvent.getData().getEvent_type(), "call.dtmf.received")) {
            System.out.println("Digit: " + telnyxEvent.getData().getPayload().getDigit());
            telnyxVoiceService.gatherStop(telnyxEvent.getData().getPayload().getCall_control_id());
        }

        Map<String, String> response = new HashMap<>();
        response.put("success", "true");
        return response;
    }
}
