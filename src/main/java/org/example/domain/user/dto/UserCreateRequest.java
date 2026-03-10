package org.example.domain.user.dto;

public record UserCreateRequest(

        String email,
        String name,
        String password
) {
}
