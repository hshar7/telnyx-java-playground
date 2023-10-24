package dev.nayzek.telnyxsdkplaygroundsb2.controller.data;

import lombok.Data;

import java.util.ArrayList;
import java.util.Date;

@Data
public class TelnyxMessageEvent {
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
            private String direction;

            private String encoding;

            private PhoneObj from;

            private ArrayList<PhoneObj> to;

            private String id;

            private Boolean is_spam;

            private String messaging_profile_id;

            private String organization_id;

            private Integer parts;

            private Date received_at;

            private String record_type;

            private String type;

            private String text;
        }
    }
}
