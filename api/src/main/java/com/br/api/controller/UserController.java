package com.br.api.controller;

import com.br.api.constants.ResponseType;
import com.br.api.constants.SecurityType;
import com.br.api.dto.request.UserRequestDTO;
import com.br.api.dto.response.UserResponseDTO;
import com.br.api.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @Operation(summary = "Busca todos os usuários cadastrados")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de usuários", content = {
                    @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = UserResponseDTO.class))
            }),
            @ApiResponse(responseCode = "403", description = ResponseType.NO_PERMISSION, content = @Content),
            @ApiResponse(responseCode = "500", description = ResponseType.ERROR, content = @Content)
    })
    @GetMapping("/all")
    @PreAuthorize("hasAuthority('ADMIN')")
    @SecurityRequirement(name = SecurityType.BEARER)
    public Map<String, Object> getAll(@RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size) {
        return userService.getAll(page, size);
    }

    @Operation(summary = "Realiza o cadastro de um novo usuário")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuário cadastrado com sucesso", content = @Content),
            @ApiResponse(responseCode = "403", description = ResponseType.NO_PERMISSION, content = @Content),
            @ApiResponse(responseCode = "500", description = ResponseType.ERROR, content = @Content)
    })
    @PostMapping("/create")
    public void create(@RequestBody @Validated UserRequestDTO userRequestDTO) {
        userService.save(userRequestDTO);
    }

    @Operation(summary = "Atualiza os dados de um usuário já existente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuário atualizado com sucesso", content = @Content),
            @ApiResponse(responseCode = "403", description = ResponseType.NO_PERMISSION, content = @Content),
            @ApiResponse(responseCode = "500", description = ResponseType.ERROR, content = @Content)
    })
    @PreAuthorize("hasAuthority('ADMIN')")
    @PutMapping("/{id}")
    @SecurityRequirement(name = SecurityType.BEARER)
    public void update(@PathVariable @NonNull Long id, @RequestBody @Validated UserRequestDTO userRequestDTO) {
        userService.update(id, userRequestDTO);
    }

    @Operation(summary = "Deleta um usuário pelo id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuário deletado com sucesso", content = @Content),
            @ApiResponse(responseCode = "403", description = ResponseType.NO_PERMISSION, content = @Content),
            @ApiResponse(responseCode = "500", description = ResponseType.ERROR, content = @Content)
    })
    @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping("/{id}")
    @SecurityRequirement(name = SecurityType.BEARER)
    public void remove(@PathVariable @NonNull Long id) {
        userService.remove(id);
    }
}