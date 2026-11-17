package com.javanauta.bffagendador.infraestructure.message.producer;

import com.javanauta.bffagendador.business.dto.out.TarefasDTOResponse;
import com.javanauta.bffagendador.infraestructure.message.config.RabbitMqConfig;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailProducer {

    private final RabbitTemplate rabbitTemplate;

    public void enviarEmail(TarefasDTOResponse dtoResponse){
        rabbitTemplate
                .convertAndSend(RabbitMqConfig
                        .EXCHANGE_EMAIL, RabbitMqConfig.ROUTING_KEY, dtoResponse);
    }
}
