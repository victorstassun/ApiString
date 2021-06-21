package br.com.senai.domain.model;


import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class Ocorrencia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;

    @ManyToOne
    private Entrega entrega;

    private String descricao;

    private LocalDateTime dataRegistro;
}
