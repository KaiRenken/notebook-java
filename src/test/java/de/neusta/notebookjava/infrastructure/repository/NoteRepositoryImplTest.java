package de.neusta.notebookjava.infrastructure.repository;

import de.neusta.notebookjava.testcontainers.AbstractDatabaseTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;

import java.util.List;

import static de.neusta.notebookjava.testdatafactories.NoteEntityTestDataFactory.aTestNoteEntity;
import static de.neusta.notebookjava.testdatafactories.NoteTestDataFactory.aTestNote;
import static org.assertj.core.api.Assertions.assertThat;

@Import(NoteRepositoryImpl.class)
class NoteRepositoryImplTest extends AbstractDatabaseTest {

    @Autowired
    private NoteRepositoryImpl noteRepositoryImpl;

    @Test
    void storeNote() {
        final var noteToStore = aTestNote().build();

        noteRepositoryImpl.store(noteToStore);

        assertThat(noteJpaRepository.count()).isEqualTo(1);

        final var storedNote = noteJpaRepository.findAll().get(0);

        assertThat(storedNote.getId()).isEqualTo(noteToStore.getId());
        assertThat(storedNote.getCreationDate()).isEqualTo(noteToStore.getCreationDate());
        assertThat(storedNote.getContent()).isEqualTo(noteToStore.getContent());
    }

    @Test
    void findAllNotes() {
        final var note1ToFind = aTestNoteEntity().withContent("test-note-1").build();
        final var note2ToFind = aTestNoteEntity().withContent("test-note-2").build();

        noteJpaRepository.saveAll(List.of(note1ToFind, note2ToFind));

        final var result = noteRepositoryImpl.findAll();

        assertThat(result).hasSize(2);
        assertThat(result.get(0).getId()).isEqualTo(note1ToFind.getId());
        assertThat(result.get(0).getCreationDate()).isEqualTo(note1ToFind.getCreationDate());
        assertThat(result.get(0).getContent()).isEqualTo(note1ToFind.getContent());
        assertThat(result.get(1).getId()).isEqualTo(note2ToFind.getId());
        assertThat(result.get(1).getCreationDate()).isEqualTo(note2ToFind.getCreationDate());
        assertThat(result.get(1).getContent()).isEqualTo(note2ToFind.getContent());
    }
}