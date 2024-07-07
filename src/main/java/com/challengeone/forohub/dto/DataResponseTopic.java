package com.challengeone.forohub.dto;

import com.challengeone.forohub.entity.TopicEntity;

import java.time.LocalDateTime;

public record DataResponseTopic(
        String title,
        String message,
        LocalDateTime creationDate) {

    public DataResponseTopic(TopicEntity topic) {
        this(topic.getTitle(), topic.getMessage(), topic.getCreationDate());
    }
}
