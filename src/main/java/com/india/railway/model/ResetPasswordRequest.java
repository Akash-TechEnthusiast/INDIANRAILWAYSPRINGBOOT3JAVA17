package com.india.railway.model;

import lombok.Data;

@Data
public class ResetPasswordRequest {
    private String token;
    private String password;
}