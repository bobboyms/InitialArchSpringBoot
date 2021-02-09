package br.com.tanos.management.record.ports.amqp;

import br.com.tanos.management.record.adapters.amqp.ParticipantMessageReceive;
import br.com.tanos.management.record.adapters.dto.ParticipantDto;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class ParticipantMessageReceiveImp implements ParticipantMessageReceive {

    @Override
    @RabbitListener(queues = "${record.rabbitmq.queue}")
    public void onReceiveMessage(ParticipantDto participantDto) {
        System.out.println("xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx");
        System.out.println(participantDto);
    }

}
