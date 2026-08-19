package com.javanauta.bffagendador.business.service;


import com.javanauta.bffagendador.business.dto.in.TarefasDTORequest;
import com.javanauta.bffagendador.business.dto.out.TarefasDTOResponse;
import com.javanauta.bffagendador.business.enums.StatusNotificacaoEnum;
import com.javanauta.bffagendador.infraestructure.client.TarefasClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TarefasService {

    private final TarefasClient client;

    public TarefasDTOResponse gravarTarefa(String token, TarefasDTORequest dto){
        return client.gravarTarefas(dto, token);
    }

    public List<TarefasDTOResponse> buscaTarefasAgendadasPorPeriodo
            (LocalDateTime dataInicial, LocalDateTime dataFinal, String token){
        return client.buscarTarefasPorPeriodo(dataInicial, dataFinal, token);

    }

    public List<TarefasDTOResponse> buscarTarefasPorEmailUsuario(String token){
        return client.buscarTarefasPorEmail(token);
    }

    public void deletaTarefaPorID(String id, String token) {
        client.deletaTarefaPorId(id, token);
    }

    public TarefasDTOResponse alterarStatus(StatusNotificacaoEnum status, String id, String token){
        return client.atualizarStatusTarefa(status, id, token);
    }

    public TarefasDTOResponse updateTarefa(TarefasDTORequest dto, String id, String token){
        return client.updateTarefas(dto, id, token);
    }


}
