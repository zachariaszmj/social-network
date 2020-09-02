package com.soft4u.socialnetwork.auth.application.controller;

import com.soft4u.socialnetwork.auth.application.dto.AuthRequestDto;
import com.soft4u.socialnetwork.auth.application.dto.BearerTokenDto;
import com.soft4u.socialnetwork.auth.application.dto.UserSignUpDto;
import com.soft4u.socialnetwork.auth.domain.model.AuthRequest;
import com.soft4u.socialnetwork.auth.domain.model.BearerToken;
import com.soft4u.socialnetwork.auth.domain.model.UserSingUp;
import com.soft4u.socialnetwork.auth.domain.service.CustomUserDetailsService;
import com.soft4u.socialnetwork.auth.domain.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class AuthenticationController {

    private final JwtUtil jwtUtil;
    private final AuthenticationManager authenticationManager;
    private final ModelMapper modelMapper;
    private final CustomUserDetailsService service;

    @GetMapping("/")
    public String welcome(Authentication authentication) {
        System.out.println(" ");
        return "Welcome user";
    }

    @PostMapping("/signUp")
    public BearerTokenDto createNewUser(@RequestBody UserSignUpDto userSignUp) {
        UserSingUp userSingUp = modelMapper.map(userSignUp, UserSingUp.class);
        UserDetails userDetails = service.addNewUser(userSingUp);
        return modelMapper.map(jwtUtil.generateToken(userDetails.getUsername()), BearerTokenDto.class);
    }

    @PostMapping("/authenticate")
    @SneakyThrows
    public BearerTokenDto generateToken(@RequestBody AuthRequestDto authRequest) {
        AuthRequest auth = modelMapper.map(authRequest, AuthRequest.class);
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(auth.getUsername(), auth.getPassword())
            );
            BearerToken source = jwtUtil.generateToken(auth.getUsername());
            return modelMapper.map(source, BearerTokenDto.class);
        } catch (Exception e) {
            throw new Exception("Invalidate credentials");
        }
    }
}
