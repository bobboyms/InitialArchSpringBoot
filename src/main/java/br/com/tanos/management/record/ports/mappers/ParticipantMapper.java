package br.com.tanos.management.record.ports.mappers;

import br.com.tanos.management.record.ports.dto.ParticipantDto;
import br.com.tanos.management.record.domain.Participant;

public interface ParticipantMapper {

    ParticipantDto create(Participant participant);
    Participant create(ParticipantDto participantDto);

}
