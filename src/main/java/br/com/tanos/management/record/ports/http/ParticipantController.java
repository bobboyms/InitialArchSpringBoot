package br.com.tanos.management.record.ports.http;

import br.com.tanos.management.record.ports.dto.ParticipantDto;
import br.com.tanos.management.record.application.ParticipantServiceImp;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/participant", produces = MediaType.APPLICATION_JSON_VALUE)
public class ParticipantController {

    private final ParticipantServiceImp participantServiceImp;

    @Autowired
    public ParticipantController(ParticipantServiceImp participantServiceImp) {
        this.participantServiceImp = participantServiceImp;
    }

    @Operation(summary = "Retorna um objeto da base de dados")
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ParticipantDto> findById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(participantServiceImp.findById(id));
    }

    @Operation(summary = "Salva um objeto na base de dados")
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ParticipantDto> save(@Valid @RequestBody ParticipantDto dto) {
        return ResponseEntity.status(HttpStatus.CREATED).
                body(participantServiceImp.save(dto));
    }

    @Operation(summary = "Atualiza um objeto na base de dados")
    @PutMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ParticipantDto> update(@PathVariable("id") Long id, @Valid @RequestBody ParticipantDto dto) {
        return ResponseEntity.ok(participantServiceImp.update(id, dto));
    }

    @Operation(summary = "Deleta um objeto na base de dados")
    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity delete(@PathVariable("id") Long id) {
        participantServiceImp.delete(id);
        return ResponseEntity.ok().build();
    }


}
