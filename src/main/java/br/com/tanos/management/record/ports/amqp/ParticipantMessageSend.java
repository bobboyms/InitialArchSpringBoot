package br.com.tanos.management.record.ports.amqp;

import br.com.tanos.management.record.ports.dto.ParticipantDto;

public interface ParticipantMessageSend {

    void sendMessage(ParticipantDto participantDto);

}
