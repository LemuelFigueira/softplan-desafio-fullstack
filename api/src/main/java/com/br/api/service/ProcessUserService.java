package com.br.api.service;

import com.br.api.dto.request.ProcessUserRequestDTO;
import com.br.api.dto.response.ProcessUserResponseDTO;
import com.br.api.mapper.ProcessUserMapper;
import com.br.api.repository.ProcessUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProcessUserService {

    private final ProcessUserRepository processUserRepository;

    public List<ProcessUserResponseDTO> getAllByProcessId(Long processId) {
        return ProcessUserMapper.entitiesToResponse(processUserRepository.findByProcess_id(processId));
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
