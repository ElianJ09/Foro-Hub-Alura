package com.alura.foro_hub.domain.topicsModels;

public record updateDataTopic(
        Long id,
        String title,
        String message,
        String course_name
) {
}