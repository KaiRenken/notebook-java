package de.neusta.notebookjava.infrastructure.note.rest.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public record CreateNoteDto(
        @JsonProperty(required = true) String content
) {
}
