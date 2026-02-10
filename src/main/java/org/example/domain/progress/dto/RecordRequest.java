package org.example.domain.progress.dto;

public record RecordRequest(
        int bibleId, // 읽은 권
        int chapterNumber // 읽은 장
) {
}
