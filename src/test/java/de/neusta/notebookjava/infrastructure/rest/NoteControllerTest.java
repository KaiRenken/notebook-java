package de.neusta.notebookjava.infrastructure.rest;

import de.neusta.notebookjava.application.NoteCreation;
import de.neusta.notebookjava.domain.NoteRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.format.DateTimeFormatter;
import java.util.List;

import static de.neusta.notebookjava.testdatafactories.NoteTestDataFactory.aTestNote;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(NoteController.class)
class NoteControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private NoteCreation noteCreationMock;

    @MockBean
    private NoteRepository noteRepositoryMock;

    @Test
    void createNote() throws Exception {
        final var createdNote = aTestNote().build();

        final var requestBody = """
                {
                    "content": "test-content"
                }
                """;

        final var expectedResponse = String.format("""
                {
                    "id": "%s",
                    "creationDate": "%s",
                    "content": "%s"
                }
                """, createdNote.getId(), createdNote.getCreationDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSSSSS")), createdNote.getContent());

        when(noteCreationMock.createNote("test-content")).thenReturn(createdNote);

        mockMvc.perform(post("/api/note")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().isCreated())
                .andExpect(content().json(expectedResponse, true));
    }

    @Test
    void retrieveAllNotes() throws Exception {
        final var note1 = aTestNote().withContent("test-content-1").build();
        final var note2 = aTestNote().withContent("test-content-2").build();

        final var expectedResponse = String.format("""
                [
                    {
                        "id": "%s",
                        "creationDate": "%s",
                        "content": "%s"
                    },
                    {
                        "id": "%s",
                        "creationDate": "%s",
                        "content": "%s"
                    }
                ]
                """, note1.getId(), note1.getCreationDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSSSSS")), note1.getContent(), note2.getId(), note2.getCreationDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSSSSS")), note2.getContent());

        when(noteRepositoryMock.findAll()).thenReturn(List.of(note1, note2));

        mockMvc.perform(get("/api/note"))
                .andExpect(status().isOk())
                .andExpect(content().json(expectedResponse, true));
    }
}