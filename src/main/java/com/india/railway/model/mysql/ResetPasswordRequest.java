package com.india.railway.model.mysql;

import lombok.Data;

@Data
public class ResetPasswordRequest {
    private String token;
    private String password;
}