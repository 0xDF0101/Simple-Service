package org.example.domain.progress;

import org.example.entity.Progress;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProgressRepository extends JpaRepository<Progress, Long> {

    Optional<Progress> findByUserIdAndBibleId(Long userId, Integer bibleId);

    List<Progress> findAllByUserId(Long userId);

}
