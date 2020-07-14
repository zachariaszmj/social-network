package com.soft4u.socialnetwork.auth.domain.service;

import com.soft4u.socialnetwork.auth.domain.model.UserSingUp;
import com.soft4u.socialnetwork.auth.domain.model.entity.User;
import com.soft4u.socialnetwork.auth.domain.repositiry.UserRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(userName)
                .orElseThrow(() -> new UsernameNotFoundException(userName));
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), Collections.emptyList());
    }

    @Transactional
    public UserDetails addNewUser(UserSingUp user) {
        if (user.getPassword() != null && user.getPassword().equals(user.getConfirmPassword())) {
            User userEntity = modelMapper.map(user, User.class);
            userEntity.setPassword(passwordEncoder.encode(userEntity.getPassword()));
            try {
                User save = userRepository.save(userEntity);
                return new org.springframework.security.core.userdetails.User(save.getUsername(), save.getPassword(), Collections.emptyList());
            } catch (Exception e) {
                throw new RuntimeException("Something went wrong " + e.getMessage());
            }
        }
        throw new RuntimeException("Something went wrong ");
    }
}
