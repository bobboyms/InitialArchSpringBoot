package br.com.tanos.management.record.ports.http;

import br.com.tanos.management.record.domain.Participant;
import br.com.tanos.management.record.ports.dto.ParticipantDto;
import br.com.tanos.management.record.ports.repository.ParticipantRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RequestCallback;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ParticipantControllerTest {

    @Autowired
    TestRestTemplate restTemplate;

    @Autowired
    ParticipantRepository participantRepository;

    @Test
    void findById() {

        ResponseEntity<ParticipantDto> responseEntity = restTemplate.
                exchange("/participant/123",
                        HttpMethod.GET,
                        null,
                        new ParameterizedTypeReference<ParticipantDto>() {});

        assertNotNull(responseEntity);
        assertEquals(204,responseEntity.getStatusCodeValue());

    }

    @Test
    void save() {

        ResponseEntity responseEntity = restTemplate.
                postForEntity("/participant",
                        new ParticipantDto("Thiago Rodrigues","88127486272"), null);

        assertEquals(201,responseEntity.getStatusCodeValue());

        Optional<Participant> participant = participantRepository.findByCpf("88127486272");
        assertEquals("88127486272",participant.get().getCpf());
        assertEquals("Thiago Rodrigues",participant.get().getName());

    }

    RequestCallback requestCallback(final Object updatedInstance) {
        return clientHttpRequest -> {
            ObjectMapper mapper = new ObjectMapper();
            mapper.writeValue(clientHttpRequest.getBody(), updatedInstance);
            clientHttpRequest.getHeaders().add(
                    HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
//            clientHttpRequest.getHeaders().add(
//                    HttpHeaders.AUTHORIZATION, "Basic " + getBase64EncodedLogPass());
        };
    }

    @Test
    void update() {

        Participant participant = participantRepository.
                save(new Participant(null, "Thiago Luiz", "2222333344425"));

        final String URI = "/participant/" + participant.getId().toString();

//        restTemplate.execute(
//                URI,
//                HttpMethod.PUT,
//                requestCallback(new ParticipantDto("Thiago Updated","2222333344425")),
//                clientHttpResponse -> null);


        MultiValueMap<String, String> headers = null;
        HttpEntity<ParticipantDto> requestUpdate =
                new HttpEntity<>(new ParticipantDto("Thiago Updated","2222333344425"), headers);
        restTemplate.
                exchange(URI, HttpMethod.PUT, requestUpdate, Void.class);

        Optional<Participant> optionalParticipant = participantRepository.findByCpf("2222333344425");
        assertEquals("2222333344425",optionalParticipant.get().getCpf());
        assertEquals("Thiago Updated",optionalParticipant.get().getName());

    }

    @Test
    void delete() {

        Participant participant = participantRepository.
                save(new Participant(null, "Thiago Luiz", "2222333344425"));

        final String URI = "/participant/" + participant.getId();

        restTemplate.delete(URI);

        Optional<Participant> optionalParticipant = participantRepository.findById(participant.getId());
        assertFalse(optionalParticipant.isPresent());

    }

}
