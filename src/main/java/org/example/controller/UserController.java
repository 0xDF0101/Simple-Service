package org.example.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.service.UserService;
import org.example.dto.user.UserCreateRequest;
import org.example.dto.user.UserInfo;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Slf4j
public class UserController {

    private final UserService userService;

    @GetMapping("/users/{userId}")
    public ResponseEntity<UserInfo> getUser(@PathVariable Long userId) { // <<< 파라미터 검증 필요

        UserInfo userinfo = userService.getUser(userId);

        return ResponseEntity.ok().body(userinfo);
    }

//    @PostMapping("/users")
//    public ResponseEntity<Void> createUser(UserCreateRequest request) {
//
//        if(request == null) {
//            throw new IllegalArgumentException(); // <<<< 커스텀 예외처리 하기
//        }

//        userService.createUser(request);
//
//        log.info("회원 등록 완료 : ", request.nickname());
//        return ResponseEntity.ok().build();
//    }


    @PostMapping("/api/v1/users")
    public ResponseEntity<Void> createUser(@RequestBody UserCreateRequest request) {

        if(request == null) {
            throw new IllegalArgumentException(); // <<<< 커스텀 예외처리 하기
        }

        userService.signUp(request);

        log.info("회원 등록 완료 : ", request.name());
        return ResponseEntity.ok().build();
    }





}
