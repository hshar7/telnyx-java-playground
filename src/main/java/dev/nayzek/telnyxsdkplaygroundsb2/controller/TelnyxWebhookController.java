package dev.nayzek.telnyxsdkplaygroundsb2.controller;

import dev.nayzek.telnyxsdkplaygroundsb2.controller.data.TelnyxEvent;
import dev.nayzek.telnyxsdkplaygroundsb2.service.TelnyxService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@RestController
public class TelnyxWebhookController {

    @Autowired
    private TelnyxService telnyxService;

    @PostMapping(value = "/api/telnyx")
    Map<String, String> dialDemo(@RequestBody TelnyxEvent telnyxEvent) {

        System.out.println(telnyxEvent);

        if (Objects.equals(telnyxEvent.getData().getEvent_type(), "call.initiated")) {
            telnyxService.answerCall(telnyxEvent.getData().getPayload().getCall_control_id());
        } else if (Objects.equals(telnyxEvent.getData().getEvent_type(), "call.answered")) {
            telnyxService.createGatherObj(
                    telnyxEvent.getData().getPayload().getCall_control_id(),
                    "https://us-central-1.telnyxstorage.com/testblink/blinktest.mp3",
                    2,
                    60000
            );
        } else if (Objects.equals(telnyxEvent.getData().getEvent_type(), "call.dtmf.received")) {
            System.out.println("Digit: " + telnyxEvent.getData().getPayload().getDigit());
            telnyxService.gatherStop(telnyxEvent.getData().getPayload().getCall_control_id());
        }

        Map<String, String> response = new HashMap<>();
        response.put("success", "true");
        return response;
    }
}
