package com.soft4u.socialnetwork;

import com.soft4u.socialnetwork.auth.domain.model.entity.User;
import com.soft4u.socialnetwork.auth.domain.repositiry.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@SpringBootApplication
public class SocialNetworkApplication {

    @Autowired
    private UserRepository userRepository;

    @PostConstruct
    public void initUsers() {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        List<User> users = Stream.of(
                new User(101, "soft4u", bCryptPasswordEncoder.encode("password"), "soft4u@gmail.com"),
                new User(102, "user1", bCryptPasswordEncoder.encode("pwd1"), "user1@gmail.com"),
                new User(103, "user2", bCryptPasswordEncoder.encode("pwd2"), "user2@gmail.com"),
                new User(104, "user3", bCryptPasswordEncoder.encode("pwd3"), "user3@gmail.com")
        ).collect(Collectors.toList());

        userRepository.saveAll(users);
    }

    public static void main(String[] args) {
        SpringApplication.run(SocialNetworkApplication.class, args);
    }

}
