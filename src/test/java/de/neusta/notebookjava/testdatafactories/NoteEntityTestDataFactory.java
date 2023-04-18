package de.neusta.notebookjava.testdatafactories;

import de.neusta.notebookjava.infrastructure.repository.model.NoteEntity;

import java.time.LocalDateTime;
import java.util.UUID;

public class NoteEntityTestDataFactory {

    private UUID id = UUID.randomUUID();
    private LocalDateTime creationDate = LocalDateTime.now();
    private String content = "test-content";

    public static NoteEntityTestDataFactory aTestNoteEntity() {
        return new NoteEntityTestDataFactory();
    }

    public NoteEntityTestDataFactory withId(UUID id) {
        this.id = id;
        return this;
    }

    public NoteEntityTestDataFactory withCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
        return this;
    }

    public NoteEntityTestDataFactory withContent(String content) {
        this.content = content;
        return this;
    }

    public NoteEntity build() {
        return new NoteEntity(id, creationDate, content);
    }
}
