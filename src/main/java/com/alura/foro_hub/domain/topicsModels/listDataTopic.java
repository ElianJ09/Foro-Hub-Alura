package com.alura.foro_hub.domain.topicsModels;

import java.time.LocalDateTime;

public record listDataTopic(Long id,
                            String title,
                            String message,
                            statusTopic status,
                            String author,
                            String course_name,
                            LocalDateTime date_topic){
    public listDataTopic(Topic topic) {
        this(topic.getId(), topic.getTitle(), topic.getMessage(), topic.getStatusTopic(), topic.getAuthor().getUsername(), topic.getCourse_name(), topic.getDate_topic());
    }
}
