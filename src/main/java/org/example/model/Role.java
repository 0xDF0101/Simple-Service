package org.example.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Role {
    USER("ROLE_USER", "회원"),
    ADMIN("ROLE_ADMIN", "관리자"),
    PRE_USER("ROLE_PRE_USER", "준회원");

    private final String key;
    private final String title;
}
