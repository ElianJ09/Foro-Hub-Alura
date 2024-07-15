package com.alura.foro_hub.controllers;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/topics")
public class topicController {
    @Autowired
    private CrudTopicService crudTopicService;

    @PostMapping
    @Transactional
    @Operation(
            summary = "Create a new topic",
            description = "We need Author, tittle and more!",
            tags = {"post"}
    )
    public ResponseEntity crear(@RequestBody @Valid DatosCrearTopico data,
                                UriComponentsBuilder uriComponentsBuilder){
        var response = crudTopicoService.crear(data);

        URI url = uriComponentsBuilder.path("/topicos/{id}").buildAndExpand(response.id()).toUri();
        return ResponseEntity.created(url).body(data);

    }
}
