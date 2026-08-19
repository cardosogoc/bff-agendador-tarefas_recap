package com.javanauta.bffagendador.business.service;

import com.javanauta.bffagendador.business.dto.in.LoginDTORequest;
import com.javanauta.bffagendador.business.dto.out.TarefasDTOResponse;
import com.javanauta.bffagendador.business.enums.StatusNotificacaoEnum;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CronService {

    private final TarefasService tarefasService;
    private final EmailService emailService;
    private final UsuarioService usuarioService;

    @Value("${usuario.email}")
    private String email;

    @Value("${usuario.senha}")
    private String senha;

    @Scheduled(cron = "${cron.horario}")
    public void buscaTarefasProximaHora(){
        String token = login(converterParaRequestDTO());
        LocalDateTime horaFutura = LocalDateTime.now().plusHours(1);
        LocalDateTime horaFuturaMaisTres = LocalDateTime.now().plusHours(1).plusMinutes(3);

        List<TarefasDTOResponse> listaTarefas = tarefasService
                .buscaTarefasAgendadasPorPeriodo(horaFutura, horaFuturaMaisTres, token);

        listaTarefas.forEach(tarefa -> {
            emailService.enviarEmail(tarefa);
            tarefasService.alterarStatus(StatusNotificacaoEnum.NOTIFICADO, tarefa.getId(), token);
        });
    }

    public String login(LoginDTORequest dto){
        return usuarioService.loginUsuario(dto);
    }

    public LoginDTORequest converterParaRequestDTO(){
        return LoginDTORequest.builder()
                .email(email)
                .senha(senha)
                .build();
    }
}
