package com.br.api.service;

import com.br.api.dto.request.ProcessUserRequestDTO;
import com.br.api.dto.response.ProcessPageableResponseDTO;
import com.br.api.dto.response.ProcessResponseDTO;
import com.br.api.dto.response.ProcessUserResponseDTO;
import com.br.api.mapper.ProcessMapper;
import com.br.api.mapper.ProcessUserMapper;
import com.br.api.repository.ProcessUserRepository;
import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class ProcessUserService {

    private final ProcessUserRepository processUserRepository;

    public List<ProcessUserResponseDTO> getAllByProcessId(Long processId) {
        return ProcessUserMapper.entitiesToResponse(processUserRepository.findByProcess_id(processId));
    }

    public ProcessPageableResponseDTO findByUserIdAndAnyNameOrProfileOrEmail(String query, int page,
            int size, Long userId) {
        var pageRequest = PageRequest.of(page, size, Sort.by("id").ascending());
        var queryProcesses = processUserRepository.findByUserIdAndAnyNameOrProfileOrEmail(query, userId, pageRequest);

        var processes = new ArrayList<ProcessResponseDTO>();

        processes = ProcessUserMapper.entitiesToProcessResponse(queryProcesses.getContent());

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
    public void save(ProcessUserRequestDTO processUserRequestDTO) {
        processUserRepository.save(ProcessUserMapper.requestToEntity(processUserRequestDTO));
    }

    public void remove(Long processId) {
        var queryProcessUser = processUserRepository.findById(processId);
        Boolean existsProcessUser = queryProcessUser.isPresent();

        if (existsProcessUser) {
            var processUser = queryProcessUser.get();
            LocalDateTime now = LocalDateTime.now();
            processUser.setDeletedAt(now);

            processUserRepository.save(processUser);
        }
    }
}
