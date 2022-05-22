package com.br.api.controller;

import com.br.api.constants.ResponseType;
import com.br.api.constants.SecurityType;
import com.br.api.dto.request.NoteRequestDTO;
import com.br.api.dto.response.NoteResponseDTO;
import com.br.api.service.NoteService;
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
@RequestMapping("note")
@SecurityRequirement(name = SecurityType.BEARER)
public class NoteController {

    private final NoteService noteService;

    @Operation(summary = "Busca todos os pareceres de um processo")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de pareceres encontrada", content = {
                    @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = NoteResponseDTO.class))
            }),
            @ApiResponse(responseCode = "403", description = ResponseType.NO_PERMISSION, content = @Content),
            @ApiResponse(responseCode = "500", description = ResponseType.ERROR, content = @Content)
    })
    @GetMapping("all/{processId}")
    public List<NoteResponseDTO> getAll(@PathVariable @NonNull Long processId) {
        return noteService.getAllByProcessId(processId);
    }

    @Operation(summary = "Salva um novo parecer")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Parecer salvo co sucesso", content = @Content),
            @ApiResponse(responseCode = "403", description = ResponseType.NO_PERMISSION, content = @Content),
            @ApiResponse(responseCode = "500", description = ResponseType.ERROR, content = @Content)
    })
    @PostMapping
    public void create(@RequestBody @Validated NoteRequestDTO noteRequestDTO) {
        noteService.save(noteRequestDTO);
    }

    @Operation(summary = "Atualiza um parecer já existente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Processo atualizado com sucesso", content = @Content),
            @ApiResponse(responseCode = "403", description = ResponseType.NO_PERMISSION, content = @Content),
            @ApiResponse(responseCode = "500", description = ResponseType.ERROR, content = @Content)
    })
    @PutMapping("/{id}")
    public void update(@PathVariable @NonNull Long id, @RequestBody @Validated NoteRequestDTO noteRequestDTO) {
        noteService.update(id, noteRequestDTO);
    }

    @Operation(summary = "Deleta um parecer já existente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Parecer deletado com sucesso", content = @Content),
            @ApiResponse(responseCode = "403", description = ResponseType.NO_PERMISSION, content = @Content),
            @ApiResponse(responseCode = "500", description = ResponseType.ERROR, content = @Content)
    })
    @DeleteMapping("/{id}")
    public void remove(@PathVariable @NonNull Long id) {
        noteService.remove(id);
    }
}