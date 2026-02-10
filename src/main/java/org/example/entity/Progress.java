package org.example.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.Collections;

@Entity
@Table(name = "progress")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EntityListeners(AuditingEntityListener.class)
public class Progress {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false) // user_id라는 이름의 FK 컬럼 설정
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "bible_id", nullable = false)
    private Bible bible;


    @Column(name = "is_completed", nullable = false)
    private Boolean completeStatus = false;

    // 읽은 장 데이터
    /**
     * 4장까지 있을 시,
     * 2,1,0,0
     * 이런 식으로 기록됨
     */
    private String progressData;
    public void updateProgressData(String updatedProgressData) {
        this.progressData = updatedProgressData;
    }

    // 마지막으로 읽은 장
    private int lastReadChapter;

    public void updateLastReadChapter(int chapter) {
        this.lastReadChapter = chapter;
    }

    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime updatedAt;

    public Progress(User user, Bible bible) {
        this.user = user;
        this.bible = bible;
        this.progressData = initData();
    }

    // 데이터 초기화
    private String initData() {
        return String.join(",", Collections.nCopies(bible.getTotalChapter(), "0"));
    }

}
