package com.br.api.dto.response;

import com.br.api.enumeration.Profile;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TokenResponseDTO {

    private String type;

    private String token;

    private UserResponseDTO user;
}