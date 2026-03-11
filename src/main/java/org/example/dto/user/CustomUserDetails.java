package org.example.dto.user;

import lombok.Getter;
import org.example.entity.User;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.List;

@Getter
public class CustomUserDetails extends org.springframework.security.core.userdetails.User {
    private final Long userId;

    public CustomUserDetails(User user) {
        super(user.getEmail(), user.getPassword(),
                List.of(new SimpleGrantedAuthority("ROLE_" + user.getRole().name())));
        this.userId = user.getId(); // 생성 시점에 ID를 저장!
    }
}
