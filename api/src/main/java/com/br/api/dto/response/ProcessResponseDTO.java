package com.br.api.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProcessResponseDTO {

    private Long id;

    private String title;

    private String subtitle;

    private String description;
}