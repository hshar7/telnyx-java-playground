package dev.nayzek.telnyxsdkplaygroundsb2.controller;

import dev.nayzek.telnyxsdkplaygroundsb2.service.TelnyxService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@RestController
public class TelnyxController {

    @Autowired
    TelnyxService telnyxService;

    @PostMapping(value = "/api/dial")
    Map<String, String> dialDemo(@RequestParam String[] numbers) {
        telnyxService.dial(
                "CALLER NUMBER HERE",
                Arrays.stream(numbers).toList(),
                "https://us-central-1.telnyxstorage.com/testblink/blinktest.mp3",
                true
        );
        Map<String, String> response = new HashMap<>();
        response.put("success", "true");
        return response;
    }
}
