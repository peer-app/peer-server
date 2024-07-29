package com.example.peer.domain.consulting.consulting.repository;

import com.example.peer.domain.consulting.consulting.entity.Consulting;
import com.example.peer.domain.consulting.consulting.entity.State;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.Optional;

public interface ConsultingRepository extends JpaRepository<Consulting, Long> , ConsultingRepositoryCustom{
    Optional<Consulting> findByConsultingDateTimeAndMentorIdAndState(LocalDateTime consultingDateTime, Long id, State state);
}
