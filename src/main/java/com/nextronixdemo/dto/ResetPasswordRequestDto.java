package com.nextronixdemo.dto;

import lombok.Data;

@Data
public class ResetPasswordRequestDto {

	private String token;
    private String newPassword;

}
