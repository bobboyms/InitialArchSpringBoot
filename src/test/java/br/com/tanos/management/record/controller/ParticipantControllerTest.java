package br.com.tanos.management.record.controller;

import br.com.tanos.management.ManagementApplication;
import br.com.tanos.management.commons.reponse.ResponseDto;
import br.com.tanos.management.record.dto.ParticipantCategoryDto;
import br.com.tanos.management.record.dto.ParticipantDto;
import br.com.tanos.management.record.dto.ParticipantTypeDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = ManagementApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ParticipantControllerTest {

    @Autowired
    protected TestRestTemplate restTemplate;

    @Test
    void getPersonType() {

        ResponseEntity responseEntity = restTemplate.
                exchange("/record/person/type",
                        HttpMethod.GET,
                        null,
                        new ParameterizedTypeReference<List<ParticipantTypeDto>>() {});

        assertNotNull(responseEntity);

        List<ParticipantTypeDto> participantTypeDtos = (List<ParticipantTypeDto>) responseEntity.getBody();

        assertEquals(2, participantTypeDtos.size());
        assertEquals(Participant.ParticipantType.FISICO, participantTypeDtos.get(0).getValue());
        assertEquals(Participant.ParticipantType.JURIDICO, participantTypeDtos.get(1).getValue());

    }

    @Test
    void getPersonCategory() {

        ResponseEntity responseEntity = restTemplate.
                exchange("/record/person/category",
                        HttpMethod.GET,
                        null,
                        new ParameterizedTypeReference<List<ParticipantCategoryDto>>() {});

        assertNotNull(responseEntity);

        List<ParticipantCategoryDto> participantCategoryDtos = (List<ParticipantCategoryDto>) responseEntity.getBody();
        System.out.println(participantCategoryDtos);

        assertEquals(4, participantCategoryDtos.size());

        assertEquals(Participant.ParticipantCategory.CUSTOMER, participantCategoryDtos.get(0).getValue());
        assertEquals(Participant.ParticipantCategory.SUPPLIER, participantCategoryDtos.get(1).getValue());
        assertEquals(Participant.ParticipantCategory.SHIPPING_COMPANY, participantCategoryDtos.get(2).getValue());
        assertEquals(Participant.ParticipantCategory.EMPLOYEE, participantCategoryDtos.get(3).getValue());

    }

    @Test
    void findById() {
    }

    @Test
    void save() {

        ParticipantDto participantDto = new ParticipantDto();
        ResponseEntity responseEntity = restTemplate.
                postForEntity("/record/person", participantDto, null);

        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());

        participantDto.setName("Jose antonio da silva");
        responseEntity = restTemplate.
                postForEntity("/record/person", participantDto, null);

        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());

        participantDto.setCpf("88127486272");
        responseEntity = restTemplate.
                postForEntity("/record/person", participantDto, null);

        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());

        participantDto.setParticipantType(Participant.ParticipantType.FISICO);
        responseEntity = restTemplate.
                postForEntity("/record/person", participantDto, null);

        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());

        participantDto.setParticipantCategory(Arrays.asList(Participant.ParticipantCategory.CUSTOMER));
        ResponseEntity sucess = restTemplate.
                postForEntity("/record/person", participantDto, ResponseDto.class);

        assertEquals(HttpStatus.CREATED, sucess.getStatusCode());
        assertNotNull(((ResponseDto)sucess.getBody()).getId());

        //Tenta recadastrar a mesma pessoa.
        participantDto.setParticipantCategory(Arrays.asList(Participant.ParticipantCategory.CUSTOMER));
        responseEntity = restTemplate.
                postForEntity("/record/person", participantDto, null);

        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());

    }

    @Test
    void update() {
    }

    @Test
    void delete() {
    }
}