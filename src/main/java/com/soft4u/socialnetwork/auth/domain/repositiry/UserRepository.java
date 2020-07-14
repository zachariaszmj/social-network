package com.soft4u.socialnetwork.auth.domain.repositiry;

import com.soft4u.socialnetwork.auth.domain.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository  extends JpaRepository<User, Long> {

    Optional<User> findByUsername(String userName);
}
