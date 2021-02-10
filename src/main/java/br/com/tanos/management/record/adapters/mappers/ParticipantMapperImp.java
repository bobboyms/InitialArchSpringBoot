package br.com.tanos.management.record.adapters.mappers;

import br.com.tanos.management.record.ports.dto.ParticipantDto;
import br.com.tanos.management.record.ports.mappers.ParticipantMapper;
import br.com.tanos.management.record.domain.Participant;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ParticipantMapperImp implements ParticipantMapper {

    private final ModelMapper modelMapper;

    @Autowired
    public ParticipantMapperImp(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public ParticipantDto create(Participant participant) {
        return modelMapper.map(participant, ParticipantDto.class);
    }

    @Override
    public Participant create(ParticipantDto participantDto) {
        return modelMapper.map(participantDto, Participant.class);
    }
}
