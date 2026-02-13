package org.example.domain.auth;

import lombok.RequiredArgsConstructor;
import org.example.domain.user.UserRepository;
import org.example.entity.User;
import org.example.model.Role;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class OAuth2UserService extends DefaultOAuth2UserService {

    private final UserRepository userRepository;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {

        // 구글로부터 유저 정보를 가져옴
        OAuth2User oAuth2User = super.loadUser(userRequest);

        Map<String, Object> attributes = oAuth2User.getAttributes();
        String name = (String) attributes.get("name");
        String email = (String) attributes.get("email");
        // 💡 1. 제공자 정보 가져오기 (google)
        String provider = userRequest.getClientRegistration().getRegistrationId();

        // 💡 2. 제공자 고유 ID 가져오기 (구글은 "sub"이라는 키를 사용해)
        String providerId = (String) attributes.get("sub");

        User user = userRepository.findByEmail(email)
                .map(entity -> {
                    entity.updateName(name); // 이름 업데이트 메서드가 있다면 활용
                    return entity;
                })
                .orElseGet(() -> {
                    // 신규 유저 생성 (생성자 파라미터 순서는 유진이 코드에 맞춰야 해!)
                    return new User(name, name, email, provider, providerId); // 일단 닉네임을 이름과 동일하게!
                });

        userRepository.save(user);

        return new DefaultOAuth2User(
                Collections.singleton(new SimpleGrantedAuthority(user.getRole().getKey())),
                attributes,
                "email"
        );
    }
}
