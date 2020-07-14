package com.soft4u.socialnetwork.auth.domain.model;

import lombok.Data;

@Data
public class UserSingUp {
    private String username;
    private String password;
    private String confirmPassword;
    private String email;
}
