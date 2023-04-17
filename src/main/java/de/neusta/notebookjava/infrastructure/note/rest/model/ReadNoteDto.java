package de.neusta.notebookjava.infrastructure.note.rest.model;

import java.time.LocalDateTime;
import java.util.UUID;

public record ReadNoteDto(
        UUID id,
        LocalDateTime creationDate,
        String content
) {
}
