package com.soft4u.socialnetwork.auth.domain.model;

import lombok.Data;

@Data
public class AuthRequest {

    private String userName;
    private String password;
}
