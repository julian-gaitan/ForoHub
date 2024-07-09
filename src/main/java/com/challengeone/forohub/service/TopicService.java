package com.challengeone.forohub.service;

import com.challengeone.forohub.dto.DataRequestTopic;
import com.challengeone.forohub.entity.TopicEntity;
import com.challengeone.forohub.reposotory.TopicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class TopicService {

    @Autowired
    private TopicRepository repository;

    public TopicEntity save(DataRequestTopic requestTopic) {
        return repository.save(new TopicEntity(requestTopic));
    }

    public TopicEntity save(TopicEntity topic) {
        return repository.save(topic);
    }

    public Page<TopicEntity> findAll(Pageable page) {
        return repository.findAll(page);
    }

    public TopicEntity getById(Long id) {
        return repository.getReferenceById(id);
    }
}
