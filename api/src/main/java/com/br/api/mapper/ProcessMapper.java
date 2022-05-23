package com.br.api.mapper;

import com.br.api.dto.response.ProcessResponseDTO;
import com.br.api.model.Process;
import com.br.api.dto.request.ProcessRequestDTO;
import com.br.api.model.User;

import java.util.ArrayList;
import java.util.List;

public final class ProcessMapper {

    public static Process requestToEntity(ProcessRequestDTO processRequestDTO) {
        return Process.builder()
                .description(processRequestDTO.getDescription())
                .subtitle(processRequestDTO.getSubtitle())
                .title(processRequestDTO.getTitle())
                .user(User.builder().id(processRequestDTO.getUserId()).build())
                .build();
    }

    public static ProcessResponseDTO entityToResponse(Process entity) {
        return ProcessResponseDTO.builder()
                .id(entity.getId())
                .title(entity.getTitle())
                .subtitle(entity.getSubtitle())
                .description(entity.getDescription())
                .deletedAt(entity.getDeletedAt())
                .createdAt(entity.getCreatedAt())
                .updatedAt(entity.getUpdatedAt())
                .userName(entity.getUser().getName()).build();
    }

    public static List<ProcessResponseDTO> entitiesToResponse(List<Process> entities) {
        var items = new ArrayList<ProcessResponseDTO>();

        entities.forEach(entity -> items.add(entityToResponse(entity)));

        return items;
    }
}
