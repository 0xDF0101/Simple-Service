package org.example.domain.user.dto;

public record UserCreateRequest(

        String name,
        String nickname,
        String email,
        String statusMessage

) {
}
