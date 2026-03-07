package org.example.service;

import org.example.dto.progress.RecordRequest;

import java.time.LocalDate;
import java.util.Map;

public interface ProgressService {
    Map<Integer, Map<Integer, Integer>> getAllProgress(Long userId);
    void recordProgress(Long userId, RecordRequest request);
    Map<LocalDate, Integer> getDailyProgress(Long userId);
}
