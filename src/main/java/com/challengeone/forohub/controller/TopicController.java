package com.challengeone.forohub.controller;

import com.challengeone.forohub.dto.DataRequestTopic;
import com.challengeone.forohub.dto.DataResponseTopic;
import com.challengeone.forohub.dto.DataUpdateTopic;
import com.challengeone.forohub.entity.TopicEntity;
import com.challengeone.forohub.service.TopicService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
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
        TopicEntity topic = topicService.save(requestTopic);
        DataResponseTopic responseTopic = new DataResponseTopic(topic);
        URI url = uriComponentsBuilder.path("/topic/{id}").buildAndExpand(topic.getId()).toUri();
        return ResponseEntity.created(url).body(responseTopic);
    }

    @GetMapping
    public ResponseEntity<Page<DataResponseTopic>> readTopics(@PageableDefault(size = 10, sort = "id") Pageable page) {
        Page<TopicEntity> topics = topicService.findAll(page);
        return ResponseEntity.ok(topics.map(DataResponseTopic::new));
    }

    @GetMapping("/{id}")
    public ResponseEntity<DataResponseTopic> readTopic(@PathVariable Long id) {
        TopicEntity topic = topicService.getById(id);
        return ResponseEntity.ok(new DataResponseTopic(topic));
    }

    @PutMapping
    public ResponseEntity<DataResponseTopic> updateTopic(@RequestBody @Valid DataUpdateTopic updateTopic) {
        TopicEntity topic = topicService.getById(updateTopic.id());
        topic.updateData(updateTopic);
        DataResponseTopic responseTopic = new DataResponseTopic(topicService.save(topic));
        return ResponseEntity.ok(responseTopic);
    }
}
