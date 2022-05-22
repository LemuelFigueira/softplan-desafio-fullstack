package com.br.api.service;

import com.br.api.dto.request.NoteRequestDTO;
import com.br.api.dto.response.NoteResponseDTO;
import com.br.api.mapper.NoteMapper;
import com.br.api.model.Note;
import com.br.api.repository.NoteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class NoteService {

    private final NoteRepository noteRepository;

    private final ProcessService processService;

    public Note getById(Long id) {
        return noteRepository.findById(id).orElseThrow();
    }

    public List<NoteResponseDTO> getAllByProcessId(Long processId) {
        return NoteMapper.entitiesToResponse(noteRepository.findAllByProcess_id(processId));
    }

    @Transactional
    public void save(NoteRequestDTO noteRequestDTO) {
        var note = NoteMapper.requestToEntity(noteRequestDTO);
        note.setProcess(processService.getById(note.getProcess().getId()));

        processService.update(note.getProcess());
        noteRepository.save(NoteMapper.requestToEntity(noteRequestDTO));
    }

    @Transactional
    public void update(Long id, NoteRequestDTO noteRequestDTO) {
        var note = getById(id);

        note.setDescription(noteRequestDTO.getDescription());
        noteRepository.save(note);
    }

    public void remove(Long id) {
        var note = getById(id);
        LocalDateTime now = LocalDateTime.now();
        note.setDeletedAt(now);
        noteRepository.save(note);
    }
}