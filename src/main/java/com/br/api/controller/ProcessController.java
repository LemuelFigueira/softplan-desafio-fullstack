package com.br.api.controller;

import java.util.List;

import com.br.api.constants.ResponseType;
import com.br.api.constants.SecurityType;
import com.br.api.dto.request.ProcessRequestDTO;
import com.br.api.dto.response.ProcessResponseDTO;
import com.br.api.service.ProcessService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

@RestController
@RequiredArgsConstructor
@RequestMapping("process")
@SecurityRequirement(name = SecurityType.BEARER)
public class ProcessController {

    private final ProcessService processService;

    @Operation(summary = "Busca todos os processos")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de processos encontrada", content = {
                    @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = ProcessResponseDTO.class))
            }),
            @ApiResponse(responseCode = "403", description = ResponseType.NO_PERMISSION, content = @Content),
            @ApiResponse(responseCode = "500", description = ResponseType.ERROR, content = @Content)
    })
    @GetMapping("all")
    public List<ProcessResponseDTO> getAll() {
        return processService.getAll();
    }

    @Operation(summary = "Busca um processo pelo id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Processo encontrada", content = {
                    @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = ProcessResponseDTO.class))
            }),
            @ApiResponse(responseCode = "403", description = ResponseType.NO_PERMISSION, content = @Content),
            @ApiResponse(responseCode = "500", description = ResponseType.ERROR, content = @Content)
    })
    @GetMapping("{id}")
    public ProcessResponseDTO getById(@PathVariable @NonNull Long id) {
        return processService.getByIdResponse(id);
    }

    @Operation(summary = "Salva um novo processo")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Processo salvo co sucesso", content = @Content),
            @ApiResponse(responseCode = "403", description = ResponseType.NO_PERMISSION, content = @Content),
            @ApiResponse(responseCode = "500", description = ResponseType.ERROR, content = @Content)
    })
    @PostMapping
    public void create(@RequestBody @Validated ProcessRequestDTO processRequestDTO) {
        processService.save(processRequestDTO);
    }

    @Operation(summary = "Atualiza um processo já existente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Processo atualizado com sucesso", content = @Content),
            @ApiResponse(responseCode = "403", description = ResponseType.NO_PERMISSION, content = @Content),
            @ApiResponse(responseCode = "500", description = ResponseType.ERROR, content = @Content)
    })
    @PutMapping("/{id}")
    public void update(@PathVariable @NonNull Long id, @RequestBody @Validated ProcessRequestDTO processRequestDTO) {
        processService.update(id, processRequestDTO);
    }

    @Operation(summary = "Deleta um processo")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Processo deletado com sucesso", content = @Content),
            @ApiResponse(responseCode = "403", description = ResponseType.NO_PERMISSION, content = @Content),
            @ApiResponse(responseCode = "500", description = ResponseType.ERROR, content = @Content)
    })
    @DeleteMapping("/{id}")
    public void remove(@PathVariable @NonNull Long id) {
        processService.remove(id);
    }
}