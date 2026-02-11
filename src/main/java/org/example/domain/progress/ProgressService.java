package org.example.domain.progress;

import org.example.domain.progress.dto.RecordRequest;

import java.time.LocalDate;
import java.util.Map;

public interface ProgressService {
    Map<Integer, Map<Integer, Integer>> getAllProgress(Long userId);
    void recordProgress(Long userId, RecordRequest request);
    Map<LocalDate, Integer> getDailyProgress(Long userId);
}
