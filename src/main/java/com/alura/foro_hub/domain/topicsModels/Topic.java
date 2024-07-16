package com.alura.foro_hub.domain.topicsModels;

import com.alura.foro_hub.domain.topicsModels.servicesTopics.updateDataTopic;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import com.alura.foro_hub.domain.usersModels.User;
import java.time.LocalDateTime;

@Table(name = "topic")
@Entity(name = "Topic")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")

public class Topic {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String message;
    private LocalDateTime date_topic;
    @Enumerated(EnumType.STRING)
    private statusTopic statusTopic;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="author_id")
    private User author;

    private String course_name;

    public Topic(
            String title,
            String message,
            statusTopic statusTopic,
            User author,
            String course_name
    ){
        this.title = title;
        this.message = message;
        this.statusTopic = statusTopic;
        this.author = author;
        this.course_name = course_name;
        this.date_topic = LocalDateTime.now();
    }


    public void updateData(updateDataTopic updateDataTopic) {
        if(updateDataTopic.title()!=null){
            this.title = updateDataTopic.title();
        }
        if(updateDataTopic.message()!=null){
            this.message = updateDataTopic.message();
        }
        if(updateDataTopic.course_name()!=null){
            this.course_name = updateDataTopic.course_name();
        }
    }
}
