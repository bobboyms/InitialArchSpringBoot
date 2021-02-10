package br.com.tanos.management.record.adapters.amqp;

import br.com.tanos.management.record.ports.amqp.ParticipantMessageSend;
import br.com.tanos.management.record.ports.dto.ParticipantDto;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ParticipantMessageSendImp implements ParticipantMessageSend {

    @Value("${record.rabbitmq.exchange}")
    private String exchange;

    @Value("${record.rabbitmq.routingKey}")
    private String routingKey;

    private final RabbitTemplate rabbitTemplate;

    public ParticipantMessageSendImp(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @Override
    public void sendMessage(ParticipantDto participantDto) {
        rabbitTemplate.convertAndSend(exchange, routingKey, participantDto);
    }
}
