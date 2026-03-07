package org.example.dto.progress;

public record RecordRequest(
        int bibleId, // 읽은 권
        int chapterNumber // 읽은 장
) {
}
