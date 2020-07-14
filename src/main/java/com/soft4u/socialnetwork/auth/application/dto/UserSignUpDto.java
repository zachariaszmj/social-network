package com.soft4u.socialnetwork.auth.application.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class UserSignUpDto {
    @JsonProperty("username")
    private String username;
    @JsonProperty("password")
    private String password;
    @JsonProperty("confirm_password")
    private String confirmPassword;
    @JsonProperty("email")
    private String email;
}
