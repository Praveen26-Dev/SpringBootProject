package com.nextronixdemo.dto;

import lombok.Data;

@Data
public class RegisterRequestDto {

    private String name;
    private String email;
    private String password;
    private String phoneNo;
}
