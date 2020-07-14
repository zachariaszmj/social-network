package com.soft4u.socialnetwork.auth.application.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class AuthRequestDto {
    @JsonProperty("username")
    private String username;
    @JsonProperty("password")
    private String password;
}
