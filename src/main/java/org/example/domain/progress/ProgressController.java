package org.example.domain.progress;

import lombok.RequiredArgsConstructor;
import org.example.domain.progress.dto.RecordRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequiredArgsConstructor
public class ProgressController {

    private final ProgressService progressService;

    // 진척도 요청
    @GetMapping("/progress")
    public ResponseEntity<Map<Integer, Map<Integer, Integer>>> getProgress(
            @RequestHeader(value = "X-USER-ID", defaultValue = "1") Long userId
    ) {
        Map<Integer, Map<Integer, Integer>> allProgress = progressService.getAllProgress(userId);

        return ResponseEntity.ok().body(allProgress);
    }

    @PostMapping("/progress")
    public ResponseEntity<Void> recordProgress(
            @RequestHeader(value = "X-USER-ID", defaultValue = "1") Long userId,
            @RequestBody RecordRequest request
            ) {

        progressService.recordProgress(userId, request);
        return ResponseEntity.ok().build();
    }

}
