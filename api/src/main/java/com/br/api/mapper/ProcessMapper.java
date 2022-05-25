package com.br.api.mapper;

import com.br.api.dto.response.ProcessPageableResponseDTO;
import com.br.api.dto.response.ProcessResponseDTO;
import com.br.api.model.Process;
import com.br.api.dto.request.ProcessRequestDTO;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public final class ProcessMapper {

    public static Process requestToEntity(ProcessRequestDTO processRequestDTO) {
        return Process.builder()
                .description(processRequestDTO.getDescription())
                .subtitle(processRequestDTO.getSubtitle())
                .title(processRequestDTO.getTitle())
                .build();
    }

    public static ProcessResponseDTO entityToResponse(Process entity) {
        return ProcessResponseDTO.builder()
                .id(entity.getId())
                .title(entity.getTitle())
                .subtitle(entity.getSubtitle())
                .description(entity.getDescription()).build();
    }

    public static List<ProcessResponseDTO> entitiesToResponse(List<Process> entities) {
        var items = new ArrayList<ProcessResponseDTO>();

        entities.forEach(entity -> items.add(entityToResponse(entity)));

        return items;
    }

    public static ProcessPageableResponseDTO pageableMapToResonse(Map<String, Object> map) {
        ObjectMapper mapper = new ObjectMapper();

        return mapper.convertValue(map, ProcessPageableResponseDTO.class);
    }
}
