package br.com.tanos.management.record.ports.dto;

import br.com.tanos.management.commons.validations.cpf.Cpf;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ParticipantDto implements Serializable {

    @NotBlank(message = "Name is required")
    @Column(nullable = false)
    private String name;

//    @Cpf
    private String cpf;

}
