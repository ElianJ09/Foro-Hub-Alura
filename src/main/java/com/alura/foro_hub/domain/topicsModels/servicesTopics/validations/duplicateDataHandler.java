package com.alura.foro_hub.domain.topicsModels.servicesTopics.validations;

import com.alura.foro_hub.domain.repositories.topicRepository;
import com.alura.foro_hub.domain.topicsModels.createDataTopic;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class duplicateDataHandler implements topicValidation{
    @Autowired
    private topicRepository topicRepository;

    public void validate(createDataTopic data){
        var title = data.title();
        var message = data.message();

        var duplicateData = topicRepository.existsByTitleAndMessage(title, message);

        if(duplicateData){
            throw new ValidationException("Data duplicated!");
        }
    }

}