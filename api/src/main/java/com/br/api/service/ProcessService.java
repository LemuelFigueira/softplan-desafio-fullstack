package com.br.api.service;

import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

import com.br.api.dto.request.ProcessRequestDTO;
import com.br.api.dto.response.ProcessPageableResponseDTO;
import com.br.api.dto.response.ProcessResponseDTO;
import com.br.api.mapper.ProcessMapper;
import com.br.api.model.Process;
import com.br.api.repository.ProcessRepository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    public ProcessPageableResponseDTO findByAnyTitleOrSubtitleOrDescription(String query, int page, int size) {
        var pageRequest = PageRequest.of(page, size, Sort.by("id").ascending());
        var queryProcesses = processRepository.findByAnyTitleOrSubtitleOrDescription(query, pageRequest);

        var processes = new ArrayList<ProcessResponseDTO>();

        queryProcesses.getContent().forEach(process -> processes.add(ProcessMapper.entityToResponse(process)));

        Map<String, Object> queryMap = new HashMap<>();

        queryMap.put("processes", processes);
        queryMap.put("totalPages", queryProcesses.getTotalPages());
        queryMap.put("totalElements", queryProcesses.getTotalElements());
        queryMap.put("isLast", queryProcesses.isLast());
        queryMap.put("isFirst", queryProcesses.isFirst());

        ProcessPageableResponseDTO response = ProcessMapper.pageableMapToResonse(queryMap);

        return response;

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