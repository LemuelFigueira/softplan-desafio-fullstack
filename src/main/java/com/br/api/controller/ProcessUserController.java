package com.br.api.controller;

import com.br.api.constants.ResponseType;
import com.br.api.constants.SecurityType;
import com.br.api.dto.request.ProcessUserRequestDTO;
import com.br.api.dto.response.ProcessUserResponseDTO;
import com.br.api.service.ProcessUserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("process-user")
@SecurityRequirement(name = SecurityType.BEARER)
public class ProcessUserController {

    private final ProcessUserService processUserService;

    @Operation(summary = "Busca todos os usuários que fazem parte do processo")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de usuários no processo encontrada", content = {
                    @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = ProcessUserResponseDTO.class))
            }),
            @ApiResponse(responseCode = "403", description = ResponseType.NO_PERMISSION, content = @Content),
            @ApiResponse(responseCode = "500", description = ResponseType.ERROR, content = @Content)
    })
    @GetMapping("all/{processId}")
    public List<ProcessUserResponseDTO> getAll(@PathVariable @NonNull Long processId) {
        return processUserService.getAllByProcessId(processId);
    }

    @Operation(summary = "Salva um novo vinculo de usuário com o processo")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Processo salvo co sucesso", content = @Content),
            @ApiResponse(responseCode = "403", description = ResponseType.NO_PERMISSION, content = @Content),
            @ApiResponse(responseCode = "500", description = ResponseType.ERROR, content = @Content)
    })
    @PostMapping
    public void create(@RequestBody @Validated ProcessUserRequestDTO processUserRequestDTO) {
        processUserService.save(processUserRequestDTO);
    }

    @Operation(summary = "Deleta um usuário de um processo")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Vinculo deletado com sucesso", content = @Content),
            @ApiResponse(responseCode = "403", description = ResponseType.NO_PERMISSION, content = @Content),
            @ApiResponse(responseCode = "500", description = ResponseType.ERROR, content = @Content)
    })
    @DeleteMapping("/{id}")
    public void remove(@PathVariable @NonNull Long id) {
        processUserService.remove(id);
    }
}
