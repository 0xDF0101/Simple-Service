package org.example.domain.progress;

import org.example.domain.progress.dto.DailyProgressDto;
import org.example.entity.DailyProgress;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface DailyProgressRepository extends JpaRepository<DailyProgress, Long> {

    Optional<DailyProgress> findByUserIdAndReadDate(Long userId, LocalDate readDate);


    @Query("SELECT new org.example.domain.progress.dto.DailyProgressDto(dp.readDate, CAST(SUM(dp.count) AS int)) " +
            "FROM DailyProgress dp " +
            "WHERE dp.user.id = :userId AND dp.readDate >= :oneYearAgo " +
            "GROUP BY dp.readDate")
    List<DailyProgressDto> getDailyReadingStats(@Param("userId") Long userId, @Param("oneYearAgo") LocalDate oneYearAgo);

}
