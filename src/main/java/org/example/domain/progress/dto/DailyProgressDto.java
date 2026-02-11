package org.example.domain.progress.dto;

import java.time.LocalDate;

public record DailyProgressDto(
        LocalDate readDate,
        int totalCount
) {
}
