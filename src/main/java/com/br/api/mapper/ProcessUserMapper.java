package com.br.api.mapper;

import com.br.api.dto.request.ProcessUserRequestDTO;
import com.br.api.dto.response.ProcessUserResponseDTO;
import com.br.api.model.Process;
import com.br.api.model.ProcessUser;
import com.br.api.model.User;

import java.util.ArrayList;
import java.util.List;

public final class ProcessUserMapper {

    public static ProcessUser requestToEntity(ProcessUserRequestDTO processRequestDTO) {
        return ProcessUser.builder()
                .process(Process.builder().id(processRequestDTO.getProcessId()).build())
                .user(User.builder().id(processRequestDTO.getUserId()).build())
                .build();
    }

    public static List<ProcessUserResponseDTO> entitiesToResponse(List<ProcessUser> entities) {
        var items = new ArrayList<ProcessUserResponseDTO>();

        entities.forEach(entity -> items.add(ProcessUserResponseDTO.builder()
                .processId(entity.getProcess().getId())
                .processTitle(entity.getProcess().getTitle())
                .userName(entity.getUser().getName())
                .userId(entity.getUser().getId())
                .createdAt(entity.getUser().getCreatedAt())
                .updatedAt(entity.getUser().getUpdatedAt())
                .deletedAt(entity.getUser().getDeletedAt())
                .build()));

        return items;
    }
}
