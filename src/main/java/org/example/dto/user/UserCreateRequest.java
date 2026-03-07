package org.example.dto.user;

public record UserCreateRequest(

        String nickname,
        String email,
        String statusMessage

) {
}
