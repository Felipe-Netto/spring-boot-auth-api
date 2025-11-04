package com.github.felipenetto.auth_api.repositories;

import com.github.felipenetto.auth_api.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

public interface UserRepositorie extends JpaRepository<User, String> {
    UserDetails findByLogin(String login);
}
