package com.br.api.dto.request;

import com.br.api.enumeration.Profile;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserRequestDTO {

    @Schema(required = true)
    private String name;

    @Schema(required = true)
    private String email;

    @Schema(required = true)
    private String password;

    @Schema(required = true)
    private Profile profile;

    public void setPassword(String password) {
        this.password = new BCryptPasswordEncoder().encode(password);
    }
}