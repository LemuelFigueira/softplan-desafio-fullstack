package com.br.api.controller;

import com.br.api.constants.ResponseType;
import com.br.api.constants.SecurityType;
import com.br.api.dto.request.TokenRequestDTO;
import com.br.api.dto.response.TokenResponseDTO;
import com.br.api.mapper.UserMapper;
import com.br.api.model.User;
import com.br.api.service.TokenService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthenticationController {

        private final AuthenticationManager authenticationManager;

        private final TokenService tokenService;

        @Operation(summary = "Processo para a geração do token")
        @ApiResponses(value = {
                        @ApiResponse(responseCode = "200", description = "Token gerado com sucesso", content = {
                                        @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = TokenResponseDTO.class))
                        }),
                        @ApiResponse(responseCode = "500", description = ResponseType.ERROR, content = @Content)
        })
        @PostMapping
        public TokenResponseDTO auth(@RequestBody @Validated TokenRequestDTO tokenRequestDTO) {
                var usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
                                tokenRequestDTO.getEmail(), tokenRequestDTO.getPassword());
                var authentication = authenticationManager.authenticate(usernamePasswordAuthenticationToken);
                var token = tokenService.generateToken(authentication);
                var user = (User) authentication.getPrincipal();
                var userDto = UserMapper.entityToResponse(user);

                return TokenResponseDTO.builder()
                                .type(SecurityType.BEARER)
                                .token(token)
                                .user(userDto)
                                .build();
        }
}
