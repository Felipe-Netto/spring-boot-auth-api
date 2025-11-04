package com.github.felipenetto.auth_api.DTOs.auth;

import com.github.felipenetto.auth_api.domain.user.UserRole;

public record RegisterDTO(String login, String password, UserRole role) {
}
