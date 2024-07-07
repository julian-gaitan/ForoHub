package com.challengeone.forohub.controller;

import com.challengeone.forohub.dto.DataRequestTopic;
import com.challengeone.forohub.dto.DataResponseTopic;
import com.challengeone.forohub.entity.TopicEntity;
import com.challengeone.forohub.service.TopicService;
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
@RequestMapping("/topic")
public class TopicController {

    @Autowired
    private TopicService topicService;


    @PostMapping
    public ResponseEntity<DataResponseTopic> createTopic(@RequestBody @Valid DataRequestTopic requestTopic,
                                                         UriComponentsBuilder uriComponentsBuilder) {
        TopicEntity topic = topicService.create(requestTopic);
        DataResponseTopic  responseTopic = new DataResponseTopic(topic);
        URI url = uriComponentsBuilder.path("/topic/{id}").buildAndExpand(topic.getId()).toUri();
        return ResponseEntity.created(url).body(responseTopic);
    }
}
