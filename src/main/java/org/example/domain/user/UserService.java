package org.example.domain.user;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.entity.User;
import org.example.exception.EntityNotFoundException;
import org.example.domain.user.dto.UserCreateRequest;
import org.example.domain.user.dto.UserInfo;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserInfo getUser(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new EntityNotFoundException("해당 회원을 찾을 수 없습니다."));
        return new UserInfo(user);
    }

    // 임시
    public void createUser(UserCreateRequest request) {
        User user = new User(request);
        userRepository.save(user);
        log.info("저장 완료 : {}", user.getNickname());
    }

    /**
     *         String name,
     *         String password,
     *         String nickname,
     *         String email,
     *         String statusMessage
     */

    // 회원 가입 로직
    @Transactional
    public void signUp(UserCreateRequest dto) {

        String encodedPassword = passwordEncoder.encode(dto.password());

        User user = new User(
                dto.name(),
                encodedPassword,
                dto.name(), // 일단 닉네임은 이름과 동일
                dto.email(),
                "Local"
        );

//        userRepository.save(user);
        userRepository.saveAndFlush(user);


        log.info("user 저장 완료");
    }


}
