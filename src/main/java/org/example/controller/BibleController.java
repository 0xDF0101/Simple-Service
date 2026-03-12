package org.example.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.dto.user.CustomUserDetails;
import org.example.service.BibleService;
import org.example.service.JpaProgressServiceImpl;
import org.example.entity.Bible;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@Slf4j
public class BibleController {

    private final BibleService bibleService;
    private final JpaProgressServiceImpl jpaProgressServiceImpl;

    @ResponseBody
    @GetMapping("/bibles/{bibleId}")
    ResponseEntity<Bible> getBible(@PathVariable int bibleId) {
        if(bibleId < 1 || bibleId > 66) {
            throw new IllegalArgumentException();
            /** TODO
             * 커스텀 예외처리 하던가 이런 검증을
             * service에서 해야할지, controller에서 해야할지 고민하기
             */
        }

        Bible bible = bibleService.getBibleInfo(bibleId);

        return ResponseEntity.ok().body(bible);
    }

    @ModelAttribute("bibles") // 별도 로직 없이 캐싱된 bible 데이터를 꺼내쓸 수 있음
    public List<Bible> bibles() {
        return bibleService.getAllBibles();
    }

    @GetMapping("/bibles")
    public String getBibles(
//            @RequestHeader(value = "X-USER-ID") Long userId,
            @AuthenticationPrincipal CustomUserDetails customUser,
            Model model
            ) {

        Long userId = customUser.getUserId();

        model.addAttribute("bibles", bibleService.getAllBibles());
        model.addAttribute("userProgress", jpaProgressServiceImpl.getAllProgress(userId));

        return "bible-list";
    }

}
