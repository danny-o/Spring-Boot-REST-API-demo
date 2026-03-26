package com.digitalskies.demo.notes;


import com.digitalskies.demo.GlobalExceptionHandler;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@RequestMapping("/notes")
@RestController
public class NotesController {

    Logger logger= LoggerFactory.getLogger(getClass());

    @Autowired
    NotesRepository notesRepository;


    @PostMapping("/create")
    Note createNote(@Valid @RequestBody NoteRequest noteRequest){

            String ownerId= (String) Objects.requireNonNull(SecurityContextHolder.getContext().getAuthentication()).getPrincipal();

            Note note=new Note();

            note.setOwnerId(ownerId);

            note.setTitle(noteRequest.getTitle());

            note.setDescription(noteRequest.getDescription());

            note.setCreatedAt(LocalDate.now());

            logger.debug("creating note {}",note);

            notesRepository.save(note);

            return note;

    }

    @GetMapping("/")
    List<Note> getNotes(){


        logger.debug("finding notes");

        String ownerId= (String) Objects.requireNonNull(SecurityContextHolder.getContext().getAuthentication()).getPrincipal();


        return notesRepository.findAllByOwnerId(ownerId);
    }


    @GetMapping(path="/{id}")
    Note getNote(@PathVariable long id){

        String ownerId= (String) Objects.requireNonNull(SecurityContextHolder.getContext().getAuthentication()).getPrincipal();

        Optional<Note> noteOptional = notesRepository.findByIdAndOwnerId(id,ownerId).stream().findFirst();

        if(noteOptional.isEmpty()){
          throw new IllegalArgumentException("Does not exist");
        }

        return noteOptional.get();
    }

    @DeleteMapping(path="/{id}")
    void deleteNote(@PathVariable long id){

        Optional<Note> noteOptional = notesRepository.findById(id);

        if(noteOptional.isEmpty()){
            throw new IllegalArgumentException("Note does not exist");
        }

        String ownerId= (String) Objects.requireNonNull(SecurityContextHolder.getContext().getAuthentication()).getPrincipal();


        if(noteOptional.get().getOwnerId().equals(ownerId)){
            notesRepository.deleteById(id);
        }

    }

    @PostMapping(path="/update-note")
    void updateNote(@RequestParam long id,@Valid @RequestBody NoteRequest noteRequest){

        Optional<Note> noteOptional = notesRepository.findById(id);

        if(noteOptional.isEmpty()){
            throw new IllegalArgumentException("Note does not exist");
        }

        String ownerId= (String) Objects.requireNonNull(SecurityContextHolder.getContext().getAuthentication()).getPrincipal();

        if(noteOptional.get().getOwnerId().equals(ownerId)){
            Note note =noteOptional.get();

            note.setTitle(noteRequest.getTitle());

            note.setDescription(noteRequest.getDescription());

            notesRepository.save(note);
        }
    }



}
