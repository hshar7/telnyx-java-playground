package dev.nayzek.telnyxsdkplaygroundsb2.controller;

import com.logmein.gotocorelib.api.common.ApiException;
import dev.nayzek.telnyxsdkplaygroundsb2.controller.data.MessageRequest;
import dev.nayzek.telnyxsdkplaygroundsb2.service.GotoMessagingService;
import dev.nayzek.telnyxsdkplaygroundsb2.service.TelnyxMessagingService;
import dev.nayzek.telnyxsdkplaygroundsb2.service.TelnyxVoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
public class GotoController {

    @Autowired
    private Environment env;

    @Autowired
    GotoMessagingService gotoMessagingService;

    @PostMapping(value = "/api/gotosms")
    Map<String, String> smsDemo(@RequestBody MessageRequest request) throws ApiException {
        gotoMessagingService.sendSMS(
                "+17372380892",
                request.getNumber(),
                request.getMessage()
        );
        Map<String, String> response = new HashMap<>();
        response.put("success", "true");
        return response;
    }
}
