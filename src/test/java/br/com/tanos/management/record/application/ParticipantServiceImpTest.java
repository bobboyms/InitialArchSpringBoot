package br.com.tanos.management.record.application;

import br.com.tanos.management.commons.config.TestRedisConfiguration;
import br.com.tanos.management.commons.exception.ObjectNotFoundException;
import br.com.tanos.management.record.adapters.service.ParticipantService;
import br.com.tanos.management.record.domain.Participant;
import br.com.tanos.management.record.ports.amqp.ParticipantMessageSend;
import br.com.tanos.management.record.ports.dto.ParticipantDto;
import br.com.tanos.management.record.ports.repository.ParticipantRepository;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {TestRedisConfiguration.class})
class ParticipantServiceImpTest {

    @MockBean
    ParticipantRepository participantRepository;

    @MockBean
    ParticipantMessageSend participantMessageSend;

    @Autowired
    ParticipantService participantService;

    @Test
    void save() {

        Mockito.when(participantRepository.save(Mockito.any(Participant.class))).
                thenReturn(new Participant(1L,"Thiago Rodrigues","88811177772"));

        ParticipantDto participantDto = participantService.
                save(new ParticipantDto("Thiago Rodrigues","88811177772"));

        assertEquals("Thiago Rodrigues", participantDto.getName());
        assertEquals("88811177772", participantDto.getCpf());

    }

    @Test
    void update() {

        Mockito.when(participantRepository.save(Mockito.any(Participant.class))).
                thenReturn(new Participant(1L,"Thiago Rodrigues","88811177772"));

        Mockito.when(participantRepository.findById(1L)).
                thenReturn(Optional.of(new Participant(1L,"Thiago Rodrigues","88811177772")));

        ParticipantDto participantDto = participantService.
                update(1L, new ParticipantDto("Thiago Rodrigues","88811177772"));

        assertEquals("Thiago Rodrigues", participantDto.getName());
        assertEquals("88811177772", participantDto.getCpf());
    }

    @Test
    void delete() {

        Mockito.when(participantRepository.findById(1L)).
                thenReturn(Optional.of(new Participant(1L,"Thiago Rodrigues","88811177772")));

        participantService.delete(1L);

        try {
            participantService.delete(2L);
        } catch (Exception e) {
            assertEquals(e.getClass(), ObjectNotFoundException.class);
        }
    }

    @Test
    void findOne() {

    }


}