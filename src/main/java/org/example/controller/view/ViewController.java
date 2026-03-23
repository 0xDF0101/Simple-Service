package org.example.controller.view;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Slf4j
public class ViewController {

    @RequestMapping("/")
    public String index() {
        log.debug("왜 안됨");
        return "index";
    }

    @GetMapping("/register")
    public String register() {
        return "register";
    }

    @GetMapping("/main")
    public String getMain() {
        return "main";
    }

    @GetMapping("/login")
    public String loginPage() { return "login"; }

    @GetMapping("/signup")
    public String signUpPage() { return "signup"; }

    // OAuth로 로그인 시, username 정하는 페이지로 리다이렉트하 하는 용도
    @GetMapping("/signup/set-username")
    public String setUsernamePage() {
        return "signup/set-username";
    }


}
