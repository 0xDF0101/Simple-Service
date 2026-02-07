package org.example.service;

import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.example.entity.Bible;
import org.example.repository.BibleRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BibleService {

    private final BibleRepository bibleRepository;

    public Bible getBibleInfo(int bibleId) {
        if(bibleId <= 0 || bibleId > 66) {
            throw new IllegalArgumentException(); // 커스텀 예외처리 해야할 듯?
        }

        return bibleRepository.findById(bibleId).orElseThrow();
    }


}
