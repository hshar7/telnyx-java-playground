package dev.nayzek.telnyxsdkplaygroundsb2.service;

import com.telnyx.sdk.ApiException;
import com.telnyx.sdk.api.CallCommandsApi;
import com.telnyx.sdk.model.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@org.springframework.stereotype.Service
public class TelnyxService {

    @Autowired
    CallCommandsApi callCommandsApi;

    public void dial(String callerNumber, List<String> phoneNumbers, String whisperUrl, boolean doRecord) {
        CallRequest callRequest = new CallRequest(); // CallRequest | Call request
        callRequest.setConnectionId("CONNECTION ID HERE");

        callRequest.from(callerNumber); // Our Telnyx number
        callRequest.audioUrl(whisperUrl); // Can optionally use .media_name() if want to upload media file to Telnyx

        callRequest.to(phoneNumbers);
        if (doRecord) {
            callRequest.record(CallRequest.RecordEnum.RECORD_FROM_ANSWER);
            callRequest.recordChannels(CallRequest.RecordChannelsEnum.DUAL);
        }
        try {
            RetrieveCallStatusResponse result = callCommandsApi.callDial(callRequest);
            System.out.println(result);
        } catch (ApiException e) {
            System.err.println("Exception when calling CallCommandsApi#callDial");
            System.err.println("Status code: " + e.getCode());
            System.err.println("Reason: " + e.getResponseBody());
            System.err.println("Response headers: " + e.getResponseHeaders());
            e.printStackTrace();
        }
    }

    public void answerCall(String callId) {
        AnswerRequest answerRequest = new AnswerRequest();
        try {
            CallControlCommandResponse result = callCommandsApi.callAnswer(callId, answerRequest);
            System.out.println(result);
        } catch (ApiException e) {
            System.err.println("Exception when calling CallCommandsApi#callAnswer");
            System.err.println("Status code: " + e.getCode());
            System.err.println("Reason: " + e.getResponseBody());
            System.err.println("Response headers: " + e.getResponseHeaders());
            e.printStackTrace();
        }
    }

    public void createGatherObj(String callId, String audioURL, int numDigits, int timeout) {
        GatherUsingAudioRequest gatherRequest = new GatherUsingAudioRequest();
        gatherRequest.audioUrl(audioURL);
        gatherRequest.setTimeoutMillis(timeout);

        // Retrofit to work
        gatherRequest.minimumDigits(numDigits);
        gatherRequest.maximumDigits(numDigits);

        try {
            CallControlCommandResponse result = callCommandsApi.callGatherUsingAudio(callId, gatherRequest);
            System.out.println(result);
        } catch (ApiException e) {
            System.err.println("Exception when calling CallCommandsApi#callGather");
            System.err.println("Status code: " + e.getCode());
            System.err.println("Reason: " + e.getResponseBody());
            System.err.println("Response headers: " + e.getResponseHeaders());
            e.printStackTrace();
        }
    }

    public void gatherStop(String callId) {
        StopGatherRequest stopGatherRequest = new StopGatherRequest();
        try {
            CallControlCommandResponse result = callCommandsApi.callGatherStop(callId, stopGatherRequest);
            System.out.println(result);
        } catch (ApiException e) {
            System.err.println("Exception when calling CallCommandsApi#callAnswer");
            System.err.println("Status code: " + e.getCode());
            System.err.println("Reason: " + e.getResponseBody());
            System.err.println("Response headers: " + e.getResponseHeaders());
            e.printStackTrace();
        }
    }
}
