package dev.nayzek.telnyxsdkplaygroundsb2.controller.data;

import lombok.Data;

@Data
public class PhoneObj {
    private String carrier;

    private String line_type;

    private String phone_number;
}