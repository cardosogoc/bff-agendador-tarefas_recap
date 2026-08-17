package com.javanauta.bffagendador.controller;


import com.javanauta.bffagendador.business.dto.in.TarefasDTORequest;
import com.javanauta.bffagendador.business.dto.out.TarefasDTOResponse;
import com.javanauta.bffagendador.business.enums.StatusNotificacaoEnum;
import com.javanauta.bffagendador.business.service.TarefasService;
import com.javanauta.bffagendador.infraestructure.security.SecurityConfig;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/tarefas")
@RequiredArgsConstructor
@Tag(name = "Tarefas", description = "Cadastro de tarefas")
@SecurityRequirement(name = SecurityConfig.SECURITY_SCHEME)
public class TarefasController {

    private final TarefasService service;

    @PostMapping
    @Operation(summary = "Salvar tarefas", description = "Cria uma nova tarefa")
    @ApiResponse(responseCode = "200", description = "Tarefa salva com sucesso")
    @ApiResponse(responseCode = "500", description = "Erro de servidor")
    public ResponseEntity<TarefasDTOResponse> gravarTarefas(@RequestBody TarefasDTORequest dto,
                                                            @RequestHeader(name = "Authorization", required = false) String token){
        return ResponseEntity.ok(service.gravarTarefa(token, dto));
    }

    @GetMapping("/eventos")
    @Operation(summary = "Buscar tarefas por periodo", description = "Buscar tarefa ja cadastradas por periodo")
    @ApiResponse(responseCode = "200", description = "Tarefa encontrada")
    @ApiResponse(responseCode = "403", description = "Tarefa não encontrada")
    @ApiResponse(responseCode = "401", description = "Credencias inválidas")
    public ResponseEntity<List<TarefasDTOResponse>> buscarTarefasPorPeriodo(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dataInicial,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dataFinal,
            @RequestHeader(name = "Authorization", required = false) String token) {

        return ResponseEntity.ok(service.buscaTarefasAgendadasPorPeriodo(dataInicial, dataFinal, token));
    }

    @GetMapping
    @Operation(summary = "Buscar lista tarefas por email", description = "Busca tarefas cadastradas por usuário")
    @ApiResponse(responseCode = "200", description = "Tarefas encontradas")
    @ApiResponse(responseCode = "403", description = "Tarefas não encontradas")
    @ApiResponse(responseCode = "500", description = "Erro de servidor")
    @ApiResponse(responseCode = "401", description = "Credencias inválidas")
    public ResponseEntity<List<TarefasDTOResponse>> buscarTarefasPorEmail(@RequestHeader(name = "Authorization", required = false) String token){
        return ResponseEntity.ok(service.buscarTarefasPorEmailUsuario(token));
    }

    @DeleteMapping
    @Operation(summary = "Deleta tarefa por id", description = "Deleta tarefa")
    @ApiResponse(responseCode = "200", description = "Tarefa deletada com sucesso")
    @ApiResponse(responseCode = "403", description = "Tarefa não encontrada")
    @ApiResponse(responseCode = "500", description = "Erro de servidor")
    @ApiResponse(responseCode = "401", description = "Credencias inválidas")
    public ResponseEntity<Void> deletaTarefaPorId(@RequestParam("id") String id,
                                                  @RequestHeader(name = "Authorization", required = false) String token){
        service.deletaTarefaPorID(id, token);

        return ResponseEntity.ok().build();
    }


    @PatchMapping
    @Operation(summary = "Atualizar status de Tarefa", description = "Atualizar status da tarefas cadastradas")
    @ApiResponse(responseCode = "200", description = "Atualizada com sucesso")
    @ApiResponse(responseCode = "403", description = "Tarefa não cadastrada")
    @ApiResponse(responseCode = "500", description = "Erro de servidor")
    @ApiResponse(responseCode = "401", description = "Credencias inválidas")
    public ResponseEntity<TarefasDTOResponse> atualizarStatusTarefa(@RequestParam("status") StatusNotificacaoEnum status,
                                                                    @RequestParam("id") String id,
                                                                    @RequestHeader(name = "Authorization", required = false) String token){
        return ResponseEntity.ok(service.alterarStatus(status, id, token));
    }

    @PutMapping
    @Operation(summary = "Atualizar Tarefa", description = "Atualizar tarefa")
    @ApiResponse(responseCode = "200", description = "Atualizada com sucesso")
    @ApiResponse(responseCode = "403", description = "Tarefa não cadastrada")
    @ApiResponse(responseCode = "500", description = "Erro de servidor")
    @ApiResponse(responseCode = "401", description = "Credencias inválidas")
    public ResponseEntity<TarefasDTOResponse> updateTarefas(@RequestBody TarefasDTORequest dto,
                                                            @RequestParam("id") String id,
                                                            @RequestHeader(name = "Authorization", required = false) String token){
        return ResponseEntity.ok(service.updateTarefa(dto, id, token));
    }

}
