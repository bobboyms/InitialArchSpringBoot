package br.com.tanos.management.record.ports.amqp;

import br.com.tanos.management.record.adapters.amqp.ParticipantMessageReceive;
import br.com.tanos.management.record.adapters.dto.ParticipantDto;
import lombok.extern.java.Log;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;


@Log
@Component
public class ParticipantMessageReceiveImp implements ParticipantMessageReceive {

    @Override
    @RabbitListener(queues = "${record.rabbitmq.queue}")
    public void onReceiveMessage(ParticipantDto participantDto) {
        log.info("xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx");
        log.info(participantDto.toString());
    }

}
