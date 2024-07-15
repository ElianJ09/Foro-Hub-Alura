package com.alura.foro_hub.domain.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

public interface userRepository extends JpaRepository<User,Long> {
    UserDetails findByEmail(String username);
}
