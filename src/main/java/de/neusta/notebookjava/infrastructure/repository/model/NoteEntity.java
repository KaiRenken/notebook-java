package de.neusta.notebookjava.infrastructure.repository.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
public class NoteEntity {

    public NoteEntity() {
    }

    public NoteEntity(final UUID id, final LocalDateTime creationDate, final String content) {
        this.id = id;
        this.creationDate = creationDate;
        this.content = content;
    }

    @Id
    private UUID id;

    private LocalDateTime creationDate;

    private String content;

    public UUID getId() {
        return id;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public String getContent() {
        return content;
    }
}
