package org.example.controller;

import lombok.RequiredArgsConstructor;
import org.example.entity.Bible;
import org.example.service.BibleService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class BibleController {

    private final BibleService bibleService;

    @RequestMapping("/bibles/{bibleId}")
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

}
