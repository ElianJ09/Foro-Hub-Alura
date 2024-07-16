package com.alura.foro_hub.domain.topicsModels.servicesTopics;

import com.alura.foro_hub.domain.repositories.topicRepository;
import com.alura.foro_hub.domain.repositories.userRepository;
import com.alura.foro_hub.domain.topicsModels.Topic;
import com.alura.foro_hub.domain.topicsModels.createDataTopic;
import com.alura.foro_hub.domain.topicsModels.servicesTopics.validations.topicValidation;
import com.alura.foro_hub.domain.topicsModels.topicDataDetails;
import com.alura.foro_hub.infra.errors.integrityValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class crudTopicService {
    @Autowired
    private topicRepository topicRepository;
    @Autowired
    private userRepository userRepository;
    @Autowired
    List<topicValidation> validations;

    public topicDataDetails createNewTopic(createDataTopic data){
        if(data.id()!=null&&!userRepository.existsById(data.id())){
            throw new integrityValidation("User not found!");
        }
        validations.forEach(validation->validation.validate(data));
        assert data.id() != null;
        var user = userRepository.findById(data.id()).get();
        var topic = new Topic(
                data.title(),
                data.message(),
                data.status(),
                user,
                data.course_Name()
        );
        topicRepository.save(topic);
        return new topicDataDetails(topic);
    }
}