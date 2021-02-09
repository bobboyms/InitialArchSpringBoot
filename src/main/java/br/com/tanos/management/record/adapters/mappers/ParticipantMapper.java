package br.com.tanos.management.record.adapters.mappers;

import br.com.tanos.management.record.adapters.dto.ParticipantDto;
import br.com.tanos.management.record.domain.Participant;

public interface ParticipantMapper {

    ParticipantDto create(Participant participant);
    Participant create(ParticipantDto participantDto);

}
