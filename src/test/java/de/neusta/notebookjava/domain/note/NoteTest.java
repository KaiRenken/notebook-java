package de.neusta.notebookjava.domain.note;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.Assertions.within;

@DisplayName("Create Note")
class NoteTest {

    @Nested
    @DisplayName("successfully")
    class CreateNoteSuccessfullyTest {

        @Test
        void withMinimalParameters() {
            final var result = new Note("test-content");

            assertThat(result.getId()).isNotNull();
            assertThat(result.getCreationDate()).isCloseTo(LocalDateTime.now(), within(1, ChronoUnit.SECONDS));
            assertThat(result.getContent()).isEqualTo("test-content");
        }

        @Test
        void withMaximalParameters() {
            final var id = UUID.randomUUID();
            final var creationDate = LocalDateTime.now();
            final var content = "test-content";

            final var result = new Note(id, creationDate, content);

            assertThat(result.getId()).isEqualTo(id);
            assertThat(result.getCreationDate()).isEqualTo(creationDate);
            assertThat(result.getContent()).isEqualTo(content);
        }
    }

    @Test
    void withInvalidContent() {
        assertThatThrownBy(() -> new Note(" ")).isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Content must not be blank");
    }

    @Test
    void withNullContent() {
        assertThatThrownBy(() -> new Note(null)).isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Content must not be null");
    }

    @Test
    void withNullCreationDate() {
        assertThatThrownBy(() -> new Note(UUID.randomUUID(), null, "test-content"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("CreationDate must not be null");
    }

    @Test
    void withNullId() {
        assertThatThrownBy(() -> new Note(null, LocalDateTime.now(), "test-content"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Id must not be null");
    }
}