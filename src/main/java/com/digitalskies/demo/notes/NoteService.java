package com.digitalskies.demo.notes;

import org.springframework.stereotype.Service;

@Service
public class NoteService {


    Note createNote(NoteRequest noteRequest){
        Note note=new Note();

        note.setTitle(noteRequest.getTitle());

        note.setDescription(noteRequest.getDescription());


        return note;
    }
}
