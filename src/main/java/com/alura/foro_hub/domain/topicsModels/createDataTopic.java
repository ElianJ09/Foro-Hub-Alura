package com.alura.foro_hub.domain.topicsModels;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

public record createDataTopic(
        @NotNull(message = "User required")
        Long id,
        @NotNull(message = "Títle required")
        String title,
        @NotNull(message = "Message required")
        String message,
        @NotNull(message = "Course Name is required")
        String course_Name,
        @NotNull(message = "Topic Status is required")
        @Valid
        statusTopic status) {
}
