package br.com.tanos.management.record.adapters.service;

import br.com.tanos.management.record.ports.dto.ParticipantDto;

public interface ParticipantService {
    void delete(Long id);
    ParticipantDto findById(Long id);
    ParticipantDto save(ParticipantDto participantDto);
    ParticipantDto update(Long id, ParticipantDto participantDto);
}
