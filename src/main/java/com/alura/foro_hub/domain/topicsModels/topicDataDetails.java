package com.alura.foro_hub.domain.topicsModels;

import java.time.LocalDateTime;

public record topicDataDetails(
        Long id,
        Long idUser,
        String title,
        String message,
        String course_name,
        LocalDateTime date_topic
){
    public topicDataDetails(Topic topic){
        this(topic.getId(), topic.getAuthor().getId(), topic.getTitle(), topic.getMessage(), topic.getCourse_name(),topic.getDate_topic());
    }
}
