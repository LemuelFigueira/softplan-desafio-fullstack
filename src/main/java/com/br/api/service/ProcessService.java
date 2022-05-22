package com.br.api.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

import com.br.api.dto.request.ProcessRequestDTO;
import com.br.api.dto.response.ProcessResponseDTO;
import com.br.api.mapper.ProcessMapper;
import com.br.api.model.Process;
import com.br.api.repository.ProcessRepository;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProcessService {

    private final ProcessRepository processRepository;

    public List<ProcessResponseDTO> getAll() {
        return ProcessMapper.entitiesToResponse(processRepository.findAll());
    }

    public Process getById(Long id) {
        return processRepository.findById(id).orElseThrow();
    }

    public ProcessResponseDTO getByIdResponse(Long id) {
        return ProcessMapper.entityToResponse(processRepository.findById(id).orElseThrow());
    }

    @Transactional
    public void save(ProcessRequestDTO processRequestDTO) {
        processRepository.save(ProcessMapper.requestToEntity(processRequestDTO));
    }

    @Transactional
    public void update(Long id, ProcessRequestDTO processRequestDTO) {
        var process = getById(id);

        process.setTitle(processRequestDTO.getTitle());
        process.setDescription(processRequestDTO.getDescription());
        process.setSubtitle(processRequestDTO.getSubtitle());

        update(process);
    }

    public void update(Process process) {
        processRepository.save(process);
    }

    public void remove(Long id) {
        var process = getById(id);
        var now = LocalDateTime.now();
        process.setDeletedAt(now);
        processRepository.save(process);
    }
}