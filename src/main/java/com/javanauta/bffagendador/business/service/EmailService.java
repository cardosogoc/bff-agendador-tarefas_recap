package com.javanauta.bffagendador.business.service;

import com.javanauta.bffagendador.business.dto.out.TarefasDTOResponse;
import com.javanauta.bffagendador.infraestructure.client.EmailClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@Service
@RequiredArgsConstructor
public class EmailService {

    private final EmailClient client;

    public void enviarEmail(@RequestBody TarefasDTOResponse dto){
        client.enviarEmail(dto);
    }
}
