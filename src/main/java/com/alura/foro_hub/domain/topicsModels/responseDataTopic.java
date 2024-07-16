package com.alura.foro_hub.domain.topicsModels;

import java.time.LocalDateTime;

public record responseDataTopic(
        Long id,
        String titl,
        String message,
        statusTopic status,
        String author,
        String name_Course,
        LocalDateTime date_topic
) {
}

