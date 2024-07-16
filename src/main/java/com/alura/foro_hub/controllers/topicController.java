package com.alura.foro_hub.controllers;

import com.alura.foro_hub.domain.topicsModels.*;
import com.alura.foro_hub.domain.topicsModels.servicesTopics.crudTopicService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/topics")
public class topicController {
    @Autowired
    private crudTopicService crudTopicService;
    @Autowired
    private com.alura.foro_hub.domain.repositories.topicRepository topicRepository;

    @PostMapping
    @Transactional
    @Operation(
            summary = "Create a new topic",
            description = "We need Author, tittle and more!",
            tags = {"post"}
    )

    public ResponseEntity createTopic(@RequestBody @Valid createDataTopic data,
                                UriComponentsBuilder uriComponentsBuilder){
        var response = crudTopicService.createNewTopic(data);

        URI url = uriComponentsBuilder.path("/topics/{id}").buildAndExpand(response.id()).toUri();
        return ResponseEntity.created(url).body(data);

    }

    @GetMapping
    @Operation(

            summary = "Get all topics",
            description = "Get all topic in order by date of creation",
            tags = {"get"}
    )
    public ResponseEntity<Page<listDataTopic>> listTopic(@PageableDefault(size=10, sort = "date_topic", direction = Sort.Direction.ASC) Pageable pageable) {
        return ResponseEntity.ok(topicRepository.findAll(pageable).map(listDataTopic::new));
    }

    @GetMapping("/filterByCourse")
    @Operation(
            summary = "Get all topic in order by course",
            description = "Get all topics in order by course from DB",
            tags = {"get"}
    )
    public ResponseEntity<Page<listDataTopic>> listTopicByCourseName(@PageableDefault(size=10, sort = "course_name", direction = Sort.Direction.ASC) Pageable pageable) {
        return ResponseEntity.ok(topicRepository.findAll(pageable).map(listDataTopic::new));
    }

    @GetMapping("/filterByYear")
    @Operation(
            summary = "Get all topics order by year",
            description = "Get all topics in order by year from DB",
            tags = {"get"}
    )
    public ResponseEntity<Page<listDataTopic>> listTopicByYear(
            @RequestParam int year,
            @PageableDefault(size = 10, sort = "date_topic", direction = Sort.Direction.ASC) Pageable pageable) {
        Page<Topic> topics = topicRepository.findByYear(year, pageable);
        Page<listDataTopic> listDataTopics = topics.map(listDataTopic::new);
        return ResponseEntity.ok(listDataTopics);
    }

    @GetMapping("/{id}")
    @Operation(
            summary = "Get topic by id",
            description = "Get indicated topic by id",
            tags = {"getById"}
    )
    public ResponseEntity<responseDataTopic> getTopicById(@PathVariable Long id){
        Topic topic = topicRepository.getReferenceById(id);
        if(topic != null){
            throw new ValidationException("Invalid topic id!");
        }
        var dataTopic = new responseDataTopic(
                topic.getId(),
                topic.getTitle(),
                topic.getMessage(),
                topic.getStatusTopic(),
                topic.getAuthor().getUsername(),
                topic.getCourse_name(),
                topic.getDate_topic());
        return ResponseEntity.ok(dataTopic);
    }

    @PutMapping
    @Transactional
    @Operation(
            summary = "Update a topic",
            description = "Update the indicate topic by id",
            tags = {"put"}
    )
    public ResponseEntity updateTopic(@RequestBody @Valid updateDataTopic datesActualizations){
        topicRepository.getReferenceById(datesActualizations.id());
        boolean topic = true;
        if(!topic){
            throw new ValidationException("Invalid topic id!");
        }
        Topic topicSelected = topicRepository.getReferenceById(datesActualizations.id());
        topicSelected.updateData(datesActualizations);
        return ResponseEntity.ok(
                new responseDataTopic(
                        topicSelected.getId(),
                        topicSelected.getTitle(),
                        topicSelected.getMessage(),
                        topicSelected.getStatusTopic(),
                        topicSelected.getAuthor().getUsername(),
                        topicSelected.getCourse_name(),
                        topicSelected.getDate_topic()
                )
        );
    }

    @DeleteMapping("/delete/{id}")
    @Transactional
    @Operation(
            summary = "Delete a topic by id",
            tags = {"delete"}
    )
    public ResponseEntity deleteTopic(@PathVariable Long id){
        Topic topic = topicRepository.getReferenceById(id);
        if(topic !=null){
            throw new ValidationException("Invalid topic id!");
        }
        topicRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
