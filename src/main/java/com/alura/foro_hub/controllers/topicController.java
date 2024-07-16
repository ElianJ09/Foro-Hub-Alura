package com.alura.foro_hub.controllers;

import com.alura.foro_hub.domain.topicsModels.createDataTopic;
import com.alura.foro_hub.domain.topicsModels.servicesTopics.crudTopicService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping("/topics")
public class topicController {
    @Autowired
    private crudTopicService crudTopicService;

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
}
