package org.example.domain.user.dto;

public record UserCreateRequest(

        String nickname,
        String email,
        String statusMessage

) {
}
