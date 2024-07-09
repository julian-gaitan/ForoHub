package com.challengeone.forohub.dto;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record DataUpdateTopic(
        @NotNull
        Long id,
        String title,
        String message) {
}
