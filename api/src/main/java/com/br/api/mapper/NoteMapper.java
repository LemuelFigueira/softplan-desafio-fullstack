package com.br.api.mapper;

import com.br.api.dto.request.NoteRequestDTO;
import com.br.api.dto.response.NoteResponseDTO;
import com.br.api.model.Note;
import com.br.api.model.User;
import com.br.api.model.Process;

import java.util.ArrayList;
import java.util.List;

public final class NoteMapper {

    public static Note requestToEntity(NoteRequestDTO noteRequestDTO) {
        return Note.builder()
                .description(noteRequestDTO.getDescription())
                .user(User.builder().id(noteRequestDTO.getUserId()).build())
                .process(Process.builder().id(noteRequestDTO.getProcessId()).build())
                .build();
    }

    public static List<NoteResponseDTO> entitiesToResponse(List<Note> entities) {
        var items = new ArrayList<NoteResponseDTO>();

        entities.forEach(entity -> items.add(NoteResponseDTO.builder()
                .id(entity.getId())
                .userId(entity.getUser().getId())
                .userName(entity.getUser().getName())
                .processId(entity.getProcess().getId())
                .createdAt(entity.getCreatedAt())
                .updatedAt(entity.getUpdatedAt())
                .deletedAt(entity.getDeletedAt())
                .description(entity.getDescription()).build()));

        return items;
    }
}
