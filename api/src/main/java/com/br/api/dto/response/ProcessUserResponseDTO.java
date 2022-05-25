package com.br.api.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProcessUserResponseDTO {

    private Long userId;

    private Long processId;

    private String userName;

    private String processTitle;
}
