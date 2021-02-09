package br.com.tanos.management.record.application;

import br.com.tanos.management.record.adapters.amqp.ParticipantMessageSend;
import br.com.tanos.management.record.adapters.dto.ParticipantDto;
import br.com.tanos.management.record.adapters.mappers.ParticipantMapper;
import br.com.tanos.management.commons.exception.ObjectNotFoundException;
import br.com.tanos.management.commons.reponse.ValidationMessage;
import br.com.tanos.management.record.domain.Participant;
import br.com.tanos.management.record.adapters.repository.ParticipantRepository;
import br.com.tanos.management.record.ports.service.ParticipantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class ParticipantServiceImp implements ParticipantService {

    private final ParticipantRepository participantRepository;
    private final ParticipantMessageSend participantMessageSend;
    private final ParticipantMapper participantMapper;

    @Autowired
    public ParticipantServiceImp(ParticipantRepository participantRepository, ParticipantMessageSend participantMessageSend, ParticipantMapper participantMapper) {
        this.participantRepository = participantRepository;
        this.participantMessageSend = participantMessageSend;
        this.participantMapper = participantMapper;
    }

    @Override
    public ParticipantDto save(ParticipantDto participantDto) {
        final Participant participant = participantMapper.create(participantDto);
        final ParticipantDto dto = participantMapper.create(participantRepository.save(participant));
        participantMessageSend.sendMessage(dto);
        return dto;
    }

    @Override
    public ParticipantDto update(Long id, ParticipantDto participantDto) {
        find(id);
        final Participant participant = participantMapper.create(participantDto);
        return participantMapper.create(participantRepository.save(participant));
    }

    @Override
    public void delete(Long id) {
        participantRepository.delete(find(id));
    }

    @Override
    public ParticipantDto findById(Long id) {
        return participantMapper.create(find(id));
    }

    public Participant find(Long id) {
        return participantRepository.findById(id).
                orElseThrow(()-> new ObjectNotFoundException(Collections.
                        singletonList(new ValidationMessage("Not found Participant"))));
    }

}
