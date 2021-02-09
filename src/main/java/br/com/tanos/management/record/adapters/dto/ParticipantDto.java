package br.com.tanos.management.record.adapters.dto;

import br.com.tanos.management.commons.validations.cpf.Cpf;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ParticipantDto {

    @NotBlank(message = "Name is required")
    @Column(nullable = false)
    private String name;

    @Cpf
    private String cpf;

}
