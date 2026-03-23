package org.example.dto.user;

public record UserCreateRequest(

        String email,
        String username,
        String password
) {
}
