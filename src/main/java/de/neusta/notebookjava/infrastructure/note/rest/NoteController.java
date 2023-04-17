package de.neusta.notebookjava.infrastructure.note.rest;

import de.neusta.notebookjava.application.note.NoteCreation;
import de.neusta.notebookjava.domain.note.Note;
import de.neusta.notebookjava.domain.note.NoteRepository;
import de.neusta.notebookjava.infrastructure.note.rest.model.CreateNoteDto;
import de.neusta.notebookjava.infrastructure.note.rest.model.ReadNoteDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class NoteController {

    private final NoteCreation noteCreation;
    private final NoteRepository noteRepository;

    public NoteController(NoteCreation noteCreation, NoteRepository noteRepository) {
        this.noteCreation = noteCreation;
        this.noteRepository = noteRepository;
    }

    @PostMapping("/api/note")
    public ResponseEntity<ReadNoteDto> createNote(@RequestBody CreateNoteDto createNoteDto) {
        final var creationResult = noteCreation.createNote(createNoteDto.content());

        return new ResponseEntity<>(mapToDto(creationResult), HttpStatus.CREATED);
    }

    @GetMapping("/api/note")
    public ResponseEntity<List<ReadNoteDto>> retrieveAllNotes() {
        final var retrievedNotes = noteRepository.findAll();

        return new ResponseEntity<>(mapToDto(retrievedNotes), HttpStatus.OK);
    }

    private ReadNoteDto mapToDto(final Note note) {
        return new ReadNoteDto(
                note.getId(),
                note.getCreationDate(),
                note.getContent()
        );
    }

    private List<ReadNoteDto> mapToDto(final List<Note> notes) {
        return notes.stream()
                .map(this::mapToDto)
                .toList();
    }
}
