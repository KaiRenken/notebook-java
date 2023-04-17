package de.neusta.notebookjava.infrastructure.note.repository;

import org.junit.jupiter.api.Test;

import static de.neusta.notebookjava.testdatafactories.NoteTestDataFactory.aTestNote;
import static org.assertj.core.api.Assertions.assertThat;

class NoteRepositoryImplTest {

    private final NoteRepositoryImpl noteRepositoryImplToTest = new NoteRepositoryImpl();

    @Test
    void storeAndFetchNotes() {
        final var note1ToStore = aTestNote().withContent("test-note-1").build();
        final var note2ToStore = aTestNote().withContent("test-note-2").build();

        noteRepositoryImplToTest.store(note1ToStore);
        noteRepositoryImplToTest.store(note2ToStore);

        final var result = noteRepositoryImplToTest.findAll();

        assertThat(result).hasSize(2);
        assertThat(result.get(0).getId()).isEqualTo(note1ToStore.getId());
        assertThat(result.get(0).getCreationDate()).isEqualTo(note1ToStore.getCreationDate());
        assertThat(result.get(0).getContent()).isEqualTo(note1ToStore.getContent());
        assertThat(result.get(1).getId()).isEqualTo(note2ToStore.getId());
        assertThat(result.get(1).getCreationDate()).isEqualTo(note2ToStore.getCreationDate());
        assertThat(result.get(1).getContent()).isEqualTo(note2ToStore.getContent());
    }
}