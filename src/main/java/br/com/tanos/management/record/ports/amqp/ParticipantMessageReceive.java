package br.com.tanos.management.record.ports.amqp;

import br.com.tanos.management.record.ports.dto.ParticipantDto;
import org.springframework.messaging.handler.annotation.Payload;

public interface ParticipantMessageReceive {

    void onReceiveMessage(@Payload ParticipantDto participantDto);

}
