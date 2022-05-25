package com.br.api.dto.response;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserPageableResponseDTO {
    private Boolean isFirst;
    private Boolean isLast;
    private Integer totalPages;
    private List<UserResponseDTO> users;
    private Integer totalElements;
}
