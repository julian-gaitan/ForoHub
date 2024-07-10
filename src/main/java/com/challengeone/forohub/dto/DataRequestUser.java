package com.challengeone.forohub.dto;

import jakarta.validation.constraints.NotBlank;

public record DataRequestUser(
        @NotBlank
        String username,
        @NotBlank
        String password
) {
}
