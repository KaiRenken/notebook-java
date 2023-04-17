package de.neusta.notebookjava.application;

import de.neusta.notebookjava.domain.NoteRepository;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.within;
import static org.mockito.Mockito.mock;

class NoteCreationTest {

    private final NoteRepository noteRepositoryMock = mock(NoteRepository.class);

    private final NoteCreation noteCreationToTest = new NoteCreation(noteRepositoryMock);

    @Test
    void createNote() {
        final var content = "test-content";

        final var result = noteCreationToTest.createNote(content);

        assertThat(result.getId()).isNotNull();
        assertThat(result.getCreationDate()).isCloseTo(LocalDateTime.now(), within(1, ChronoUnit.SECONDS));
        assertThat(result.getContent()).isEqualTo("test-content");
    }
}