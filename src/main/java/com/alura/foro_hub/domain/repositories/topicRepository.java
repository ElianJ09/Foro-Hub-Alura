package com.alura.foro_hub.domain.repositories;

import com.alura.foro_hub.domain.topicsModels.Topic;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface topicRepository extends JpaRepository<Topic,Long> {
    Boolean existsByTitleAndMensaje(String title, String message);
    @Query("SELECT t FROM topic t WHERE FUNCTION('YEAR', t.date_topic) = :year")
    Page<Topic> findByYear(@Param("year") int year, Pageable pageable);
}
