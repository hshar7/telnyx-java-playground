package dev.nayzek.telnyxsdkplaygroundsb2.service;

import com.logmein.gotocorelib.api.common.ApiException;
import com.logmein.gotocorelib.api.common.ApiInvoker;
import kong.unirest.core.HttpResponse;
import kong.unirest.core.JsonNode;
import kong.unirest.core.Unirest;
import org.apache.http.entity.ContentType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;

import java.util.HashMap;

@org.springframework.stereotype.Service
public class GotoMessagingService {

    @Autowired
    private InMemoryDataService inMemoryDataService;

    @Autowired
    private Environment env;

    public void sendSMS(String from, String to, String message) throws ApiException {
        HashMap<String, String> body = new HashMap<>();
        body.put("ownerPhoneNumber", from);
        body.put("contactPhoneNumber", to);
        body.put("body", message);

        HashMap<String, String> headers = new HashMap<>();
        headers.put("Authorization", "Bearer " + gotoGetAccessToken());

        new ApiInvoker().invokeAPI(
                "https://api.goto.com",
                "/messaging/v1/messages",
                "POST",
                new HashMap<>(),
                body,
                headers,
                new HashMap<>(),
                ContentType.APPLICATION_JSON.toString()
        );
    }

    private String gotoGetAccessToken() {

        if (inMemoryDataService.getData("access_token") != null) {
            return inMemoryDataService.getData("access_token");
        }

        HttpResponse<JsonNode> response = Unirest.post("https://authentication.logmeininc.com/oauth/token")
                .header("Content-Type", "application/x-www-form-urlencoded")
                .header("User-Agent", "insomnia/2023.5.8")
                .header("Authorization", "Basic " + env.getProperty("goto.basicauth"))
                .body("redirect_url=" + env.getProperty("goto.redirecturl") + "&grant_type=authorization_code&code=eyJraWQiOiI2MjAiLCJhbGciOiJSUzUxMiJ9.eyJzYyI6ImNhbGwtY29udHJvbC52MS5jYWxscy5jb250cm9sIGNhbGwtZXZlbnRzLnYxLmV2ZW50cy5yZWFkIGNhbGwtZXZlbnRzLnYxLm5vdGlmaWNhdGlvbnMubWFuYWdlIGNhbGwtaGlzdG9yeS52MS5ub3RpZmljYXRpb25zLm1hbmFnZSBjYWxscy52Mi5pbml0aWF0ZSBjci52MS5yZWFkIGZheC52MS5ub3RpZmljYXRpb25zLm1hbmFnZSBmYXgudjEucmVhZCBmYXgudjEud3JpdGUgaWRlbnRpdHk6IG1lc3NhZ2luZy52MS5ub3RpZmljYXRpb25zLm1hbmFnZSBtZXNzYWdpbmcudjEucmVhZCBtZXNzYWdpbmcudjEuc2VuZCBtZXNzYWdpbmcudjEud3JpdGUgcHJlc2VuY2UudjEubm90aWZpY2F0aW9ucy5tYW5hZ2UgcHJlc2VuY2UudjEucmVhZCBwcmVzZW5jZS52MS53cml0ZSB1c2Vycy52MS5saW5lcy5yZWFkIHZvaWNlLWFkbWluLnYxLnJlYWQgdm9pY2UtYWRtaW4udjEud3JpdGUgdm9pY2VtYWlsLnYxLm5vdGlmaWNhdGlvbnMubWFuYWdlIHZvaWNlbWFpbC52MS52b2ljZW1haWxzLnJlYWQgdm9pY2VtYWlsLnYxLnZvaWNlbWFpbHMud3JpdGUgd2VicnRjLnYxLnJlYWQgd2VicnRjLnYxLndyaXRlIiwic3ViIjoiNTEwMTUzNzU3MTM4MDQ3MDIxMSIsImF1ZCI6IjllMGQwZGFiLTgwOTQtNDNhZS1hMjNmLTA0YWZiYmM3MGEwMCIsIm9nbiI6InB3ZCIsImxzIjoiNjliYmMwZWYtMTgzOS00OGE5LWJiMDktNTI2YjI5NDRmN2UwIiwidHlwIjoiYyIsImV4cCI6MTcwMjkwNDc4NSwiaWF0IjoxNzAyOTA0MTg1LCJqdGkiOiJjYjZjYjY2MC1iZjE3LTRiYTMtOTQzZS05YjVlZDNhMThlY2QifQ.xbDRoB7JxZDkQyf6E0GUlAHlL5S37C8jHo9RXSsWpCkL2yLQ-RHKPX77Z-EMl-yWYkwSfTeBa_LVe4jSItVD0fVXkRbY1cUVpjDgR-di94WskN_wri_fQFr5JSQubMRCjfukYcSJWG4Z2B0YvzqmcZkVm_heDSk3LAnj-r5sxYraDFtwkKSYhspF5e2j8pMrheWLUCGHoYiqwT40bZBaDlz2sLUMbez0Fb-TyXNhCNlXM1bKsQeQ6qj73z2u4-xByf1hR_T0REIHf0ysWCQF14Vk4hRBrGnydssp6GYaq_C0GXkHazy0haWXVMLum_FK6Ncw_ThTQnv30Nk1VeBxZA")
                .asJson();

        inMemoryDataService.putData("access_token", response.getBody().getObject().getString("access_token"));
        return response.getBody().getObject().getString("access_token");
    }
}
