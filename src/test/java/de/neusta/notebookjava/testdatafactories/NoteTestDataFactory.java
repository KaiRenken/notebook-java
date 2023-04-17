package de.neusta.notebookjava.testdatafactories;

import de.neusta.notebookjava.domain.Note;

import java.time.LocalDateTime;
import java.util.UUID;

public class NoteTestDataFactory {

    private UUID id = UUID.randomUUID();
    private LocalDateTime creationDate = LocalDateTime.now();
    private String content = "test-content";

    public static NoteTestDataFactory aTestNote() {
        return new NoteTestDataFactory();
    }

    public NoteTestDataFactory withId(UUID id) {
        this.id = id;
        return this;
    }

    public NoteTestDataFactory withCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
        return this;
    }

    public NoteTestDataFactory withContent(String content) {
        this.content = content;
        return this;
    }

    public Note build() {
        return new Note(id, creationDate, content);
    }
}
