package com.soft4u.socialnetwork.auth.domain.model;

import lombok.Data;

@Data
public class AuthRequest {

    private String username;
    private String password;
}
