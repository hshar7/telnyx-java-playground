package dev.nayzek.telnyxsdkplaygroundsb2.controller.data;

import lombok.Data;

import java.util.Date;

@Data
public class TelnyxEvent {
    private DataObj data;

    private MetaObj meta;


    @Data
    public class MetaObj {
        private Integer attempt;

        private String delivered_to;
    }

    @Data
    public class DataObj {
        private String event_type;

        private String id;

        private Date occurred_at;

        private Payload payload;

        private String record_type;

        @Data
        public class Payload {
            private String call_control_id;

            private String call_leg_id;

            private String call_session_id;

            private String client_state;

            private String connection_id;

            private Date end_time;

            private String from;

            private String hangup_cause;

            private String hangup_source;

            private String sip_hangup_cause;

            private Date start_time;

            private String to;

            private String digit;
        }
    }
}
