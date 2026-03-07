package org.example.dto.progress;

import java.time.LocalDate;

public record DailyProgressDto(
        LocalDate readDate,
        int totalCount
) {
}
