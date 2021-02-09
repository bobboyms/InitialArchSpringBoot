package br.com.tanos.management.record.adapters.amqp;

import br.com.tanos.management.record.adapters.dto.ParticipantDto;

public interface ParticipantMessageSend {

    void sendMessage(ParticipantDto participantDto);

}
