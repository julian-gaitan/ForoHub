package com.challengeone.forohub.service;

import com.challengeone.forohub.dto.DataRequestTopic;
import com.challengeone.forohub.entity.TopicEntity;
import com.challengeone.forohub.reposotory.TopicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TopicService {

    @Autowired
    private TopicRepository repository;

    public TopicEntity create(DataRequestTopic requestTopic) {
        return repository.save(new TopicEntity(requestTopic));
    }
}
