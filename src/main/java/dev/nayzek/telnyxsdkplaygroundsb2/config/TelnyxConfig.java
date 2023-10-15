package dev.nayzek.telnyxsdkplaygroundsb2.config;

import com.telnyx.sdk.ApiClient;
import com.telnyx.sdk.Configuration;
import com.telnyx.sdk.api.CallCommandsApi;
import com.telnyx.sdk.auth.HttpBearerAuth;
import org.springframework.context.annotation.Bean;

@org.springframework.context.annotation.Configuration
public class TelnyxConfig {

    @Bean
    public static CallCommandsApi callCommandsApi() {
        ApiClient defaultClient = Configuration.getDefaultApiClient();
        defaultClient.setBasePath("https://api.telnyx.com/v2");

        // Configure HTTP bearer authorization: bearerAuth
        HttpBearerAuth bearerAuth = (HttpBearerAuth) defaultClient.getAuthentication("bearerAuth");
        bearerAuth.setBearerToken("BEARER TOKEN HERE");

        return new CallCommandsApi(defaultClient);
    }
}
