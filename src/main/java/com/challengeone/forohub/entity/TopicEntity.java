package com.challengeone.forohub.entity;

import com.challengeone.forohub.dto.DataRequestTopic;
import com.challengeone.forohub.dto.DataUpdateTopic;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Table(name = "topics")
@Entity(name = "Topic")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class TopicEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique=true)
    private String title;
    private String message;
    private LocalDateTime creationDate;

    public TopicEntity(DataRequestTopic requestTopic) {
        this.title = requestTopic.title();
        this.message = requestTopic.message();
        this.creationDate = requestTopic.creationDate();
    }

    public void updateData(DataUpdateTopic updateTopic) {
        if (updateTopic.title() != null) {
            this.title = updateTopic.title();
        }
        if (updateTopic.message() != null) {
            this.message = updateTopic.message();
        }
    }
}
