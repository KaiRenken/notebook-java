package de.neusta.notebookjava.domain.note;

import java.time.LocalDateTime;
import java.util.UUID;

public class Note {

    private final UUID id;
    private final LocalDateTime creationDate;
    private final String content;

    public Note(final UUID id, final LocalDateTime creationDate, final String content) {
        this.id = id;
        this.creationDate = creationDate;
        this.content = content;

        requireIdNotNull(id);
        requireCreationDateNotNull(creationDate);
        requireContentNotNull(content);

        requireContentNotBlank(content);
    }

    public Note(final String content) {
        this(UUID.randomUUID(), LocalDateTime.now(), content);
    }

    public UUID getId() {
        return id;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public String getContent() {
        return content;
    }

    private void requireIdNotNull(final UUID id) {
        if (id == null) {
            throw new IllegalArgumentException("Id must not be null");
        }
    }

    private void requireCreationDateNotNull(final LocalDateTime creationDate) {
        if (creationDate == null) {
            throw new IllegalArgumentException("CreationDate must not be null");
        }
    }

    private void requireContentNotNull(final String content) {
        if (content == null) {
            throw new IllegalArgumentException("Content must not be null");
        }
    }

    private void requireContentNotBlank(final String content) {
        if (content.isBlank()) {
            throw new IllegalArgumentException("Content must not be blank");
        }
    }
}
