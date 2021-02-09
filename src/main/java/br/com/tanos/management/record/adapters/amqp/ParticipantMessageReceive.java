package br.com.tanos.management.record.adapters.amqp;

import br.com.tanos.management.record.adapters.dto.ParticipantDto;
import org.springframework.messaging.handler.annotation.Payload;

public interface ParticipantMessageReceive {

    void onReceiveMessage(@Payload ParticipantDto participantDto);

}
