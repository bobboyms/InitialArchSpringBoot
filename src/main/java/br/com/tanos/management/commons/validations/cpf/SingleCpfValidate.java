package br.com.tanos.management.commons.validations.cpf;

import br.com.tanos.management.record.adapters.repository.ParticipantRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class SingleCpfValidate implements ConstraintValidator<SingleCpf, String> {

    private final ParticipantRepository participantRepository;

    @Autowired
    public SingleCpfValidate(ParticipantRepository participantRepository) {
        this.participantRepository = participantRepository;
    }

    @Override
    public boolean isValid(String cpf, ConstraintValidatorContext context) {
        return cpf != null && !participantRepository.findByCpf(cpf).isPresent();
    }
}
