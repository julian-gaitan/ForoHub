package com.challengeone.forohub.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record DataRequestTopic(
        @NotBlank
        String title,
        @NotBlank
        String message,
        @NotNull
        LocalDateTime creationDate
) {
}
