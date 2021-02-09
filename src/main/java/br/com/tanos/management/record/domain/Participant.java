package br.com.tanos.management.record.domain;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "tb_participant")
public class Participant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String cpf;

}
