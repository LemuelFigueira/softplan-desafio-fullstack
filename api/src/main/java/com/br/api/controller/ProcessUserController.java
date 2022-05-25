package com.br.api.controller;

import com.br.api.constants.ResponseType;
import com.br.api.constants.SecurityType;
import com.br.api.dto.request.ProcessUserRequestDTO;
import com.br.api.dto.response.ProcessPageableResponseDTO;
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
import org.springframework.security.access.prepost.PreAuthorize;
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
    @PreAuthorize("hasAnyAuthority('SORTER', 'ADMIN')")
    @GetMapping("all/{processId}")
    public List<ProcessUserResponseDTO> getAll(@PathVariable @NonNull Long processId) {
        return processUserService.getAllByProcessId(processId);
    }

    @Operation(summary = "Busca todos os processos associados à um usuário por title, subtitle ou description")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de processos", content = {
                    @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = ProcessPageableResponseDTO.class))
            }),
            @ApiResponse(responseCode = "403", description = ResponseType.NO_PERMISSION, content = @Content),
            @ApiResponse(responseCode = "500", description = ResponseType.ERROR, content = @Content)
    })
    @GetMapping("/search")
    @SecurityRequirement(name = SecurityType.BEARER)
    public ProcessPageableResponseDTO findByUserIdAndAnyNameOrProfileOrEmail(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size,
            @RequestParam(defaultValue = "") String query,
            @RequestParam(required = true) Long userId) {
        return processUserService.findByUserIdAndAnyNameOrProfileOrEmail(query, page, size, userId);
    }

    @Operation(summary = "Salva um novo vinculo de usuário com o processo")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Processo salvo co sucesso", content = @Content),
            @ApiResponse(responseCode = "403", description = ResponseType.NO_PERMISSION, content = @Content),
            @ApiResponse(responseCode = "500", description = ResponseType.ERROR, content = @Content)
    })
    @PreAuthorize("hasAnyAuthority('SORTER', 'ADMIN')")
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
    @PreAuthorize("hasAnyAuthority('SORTER', 'ADMIN')")
    @DeleteMapping("/{id}")
    public void remove(@PathVariable @NonNull Long id) {
        processUserService.remove(id);
    }
}
