package com.javanauta.bffagendador.controller;

import com.javanauta.bffagendador.business.dto.in.EnderecoDTORequest;
import com.javanauta.bffagendador.business.dto.in.LoginDTORequest;
import com.javanauta.bffagendador.business.dto.in.TelefoneDTORequest;
import com.javanauta.bffagendador.business.dto.in.UsuarioDTORequest;
import com.javanauta.bffagendador.business.dto.out.EnderecoDTOResponse;
import com.javanauta.bffagendador.business.dto.out.TelefoneDTOResponse;
import com.javanauta.bffagendador.business.dto.out.UsuarioDTOResponse;
import com.javanauta.bffagendador.business.dto.out.ViaCepDTOResponse;
import com.javanauta.bffagendador.business.service.UsuarioService;
import com.javanauta.bffagendador.infraestructure.security.SecurityConfig;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usuario")
@RequiredArgsConstructor
@Tag(name = "Usuário", description = "Cadastro e login de usuários")
@SecurityRequirement(name = SecurityConfig.SECURITY_SCHEME)
public class UsuarioController {

    private final UsuarioService service;

    @PostMapping("/login")
    @Operation(summary = "Login Usuários", description = "Login do usuário")
    @ApiResponse(responseCode = "200", description = "Usuário logado com sucesso")
    @ApiResponse(responseCode = "401", description = "Credencias inválidas")
    @ApiResponse(responseCode = "500", description = "Erro de servidor")
    public ResponseEntity<String> login(@RequestBody LoginDTORequest dto){
        return ResponseEntity.ok(service.loginUsuario(dto));
    }

    @PostMapping
    @Operation(summary = "Salvar Usuários", description = "Cria um novo usuário")
    @ApiResponse(responseCode = "200", description = "Usuário salvo com sucesso")
    @ApiResponse(responseCode = "409", description = "Usuário já cadastrado")
    @ApiResponse(responseCode = "500", description = "Erro de servidor")
    public ResponseEntity<UsuarioDTOResponse> salvaUsuario(@RequestBody UsuarioDTORequest dto){
        return ResponseEntity.ok(service.salvaUsuario(dto));
    }

    //Pesquisando o usuario por meio do body
    @GetMapping("/pesquisa")
    @Operation(summary = "Buscar dados de Usuários por email", description = "Buscar dados do usuário")
    @ApiResponse(responseCode = "200", description = "Usuário encontrado")
    @ApiResponse(responseCode = "403", description = "Usuário não encontrado")
    @ApiResponse(responseCode = "500", description = "Erro de servidor")
    @ApiResponse(responseCode = "401", description = "Credencias inválidas")
    public ResponseEntity<UsuarioDTOResponse> buscarUsuarioPorEmail(@RequestBody EmailRequest request,
                                                                    @RequestHeader(name = "Authorization", required = false) String token) {
        return ResponseEntity.ok(service.buscarUsuarioPorEmail(request.email(), token));
    }

    public record EmailRequest(String email) {}

    //Pesquisando Usuario por meio de parâmetro
    @GetMapping
    @Operation(summary = "Buscar dados de Usuários por email", description = "Buscar dados do usuário")
    @ApiResponse(responseCode = "200", description = "Usuário encontrado")
    @ApiResponse(responseCode = "403", description = "Usuário não encontrado")
    @ApiResponse(responseCode = "500", description = "Erro de servidor")
    @ApiResponse(responseCode = "401", description = "Credencias inválidas")
    public ResponseEntity<UsuarioDTOResponse> buscaUsuarioPorEmail(@RequestParam("email") String email,
                                                                   @RequestHeader(name = "Authorization", required = false) String token){
        return ResponseEntity.ok(service.buscarUsuarioPorEmail(email, token));
    }

    @DeleteMapping("/{email}")
    @Operation(summary = "Deleta Usuários por email", description = "Deleta usuário")
    @ApiResponse(responseCode = "200", description = "Usuário deletado com sucesso")
    @ApiResponse(responseCode = "403", description = "Usuário não encontrado")
    @ApiResponse(responseCode = "500", description = "Erro de servidor")
    @ApiResponse(responseCode = "401", description = "Credencias inválidas")
    public ResponseEntity<Void> deletaUsuarioPorEmail(@PathVariable String email,
                                                      @RequestHeader(name = "Authorization", required = false) String token){
        service.deletaUsuarioPorEmail(email, token);
        return ResponseEntity.ok().build();
    }

    @PutMapping
    @Operation(summary = "Atualizar dados de Usuários", description = "Atualizar dados de Usuários")
    @ApiResponse(responseCode = "200", description = "Atualizado com sucesso")
    @ApiResponse(responseCode = "403", description = "Usuário não cadastrado")
    @ApiResponse(responseCode = "500", description = "Erro de servidor")
    @ApiResponse(responseCode = "401", description = "Credencias inválidas")
    public ResponseEntity<UsuarioDTOResponse> atualizaUsuario(
            @RequestBody UsuarioDTORequest dto,
            @RequestHeader(name = "Authorization", required = false) String token){
        return ResponseEntity.ok(service.atualizaDadosUsuario(dto, token));
    }

    @PutMapping("/endereco")
    @Operation(summary = "Atualizar endereço de Usuários", description = "Atualizar endereço de Usuários")
    @ApiResponse(responseCode = "200", description = "Endereço Atualizado com sucesso")
    @ApiResponse(responseCode = "403", description = "Usuário não encontrado")
    @ApiResponse(responseCode = "500", description = "Erro de servidor")
    @ApiResponse(responseCode = "401", description = "Credencias inválidas")
    public ResponseEntity<EnderecoDTOResponse> atualizaEndereco(
            @RequestBody EnderecoDTORequest dto,
            @RequestParam("id") Long id,
            @RequestHeader(name = "Authorization", required = false) String token){
        return ResponseEntity.ok(service.atualizaEndereco(id, dto, token));
    }

    @PutMapping("/telefone")
    @Operation(summary = "Atualizar telefone de Usuários", description = "Atualizar telefone de Usuários")
    @ApiResponse(responseCode = "200", description = "Telefone Atualizado com sucesso")
    @ApiResponse(responseCode = "403", description = "Usuário não encontrado")
    @ApiResponse(responseCode = "500", description = "Erro de servidor")
    @ApiResponse(responseCode = "401", description = "Credencias inválidas")
    public ResponseEntity<TelefoneDTOResponse> atualizaTelefone(
            @RequestBody TelefoneDTORequest dto,
            @RequestParam("id") Long id,
            @RequestHeader(name = "Authorization", required = false) String token){
        return ResponseEntity.ok(service.atualizaTelefone(id, dto, token));
    }

    @PostMapping("/endereco")
    @Operation(summary = "Salva endereço de Usuários", description = "Salva endereço de Usuários")
    @ApiResponse(responseCode = "200", description = "Endereço Salvo com sucesso")
    @ApiResponse(responseCode = "403", description = "Usuário não encontrado")
    @ApiResponse(responseCode = "500", description = "Erro de servidor")
    @ApiResponse(responseCode = "401", description = "Credencias inválidas")
    public ResponseEntity<EnderecoDTOResponse> cadastroEndereco(
            @RequestBody EnderecoDTORequest dto,
            @RequestHeader(name = "Authorization", required = false) String token
    ){
        return ResponseEntity.ok(service.cadastroEndereco(token, dto));
    }

    @PostMapping("/telefone")
    @Operation(summary = "Salva telefone de Usuários", description = "Salva telefone de Usuários")
    @ApiResponse(responseCode = "200", description = "Telefone Salvo com sucesso")
    @ApiResponse(responseCode = "403", description = "Usuário não encontrado")
    @ApiResponse(responseCode = "500", description = "Erro de servidor")
    @ApiResponse(responseCode = "401", description = "Credencias inválidas")
    public ResponseEntity<TelefoneDTOResponse> cadastroTelefone(
            @RequestBody TelefoneDTORequest dto,
            @RequestHeader(name = "Authorization", required = false) String token
    ){
        return ResponseEntity.ok(service.cadastroTelefone(token, dto));
    }

    @GetMapping("/endereco/{cep}")
    @Operation(summary = "Busca Endereço pelo CEP", description = "Dados de endereço por meio do Cep")
    @ApiResponse(responseCode = "200", description = "Cep Encontrado com sucesso")
    @ApiResponse(responseCode = "400", description = "Cep inválido")
    @ApiResponse(responseCode = "500", description = "Erro de servidor")
    public ResponseEntity<ViaCepDTOResponse> buscarEnderecoPorCep(@PathVariable("cep") String cep){
        return ResponseEntity.ok(service.buscarEnderecoPorCep(cep));
    }
}
