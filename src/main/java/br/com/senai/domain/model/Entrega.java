package br.com.senai.domain.model;

import br.com.senai.domain.exception.NegocioException;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;


import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "entregas")
public class Entrega {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "pessoa_id")
	private Pessoa pessoa;

	@Embedded
	private Destinatario destinatario;

	private BigDecimal taxa;

	@OneToMany(mappedBy = "entrega", cascade = CascadeType.ALL)
	private List<Ocorrencia> ocorrencias = new ArrayList<>();

	@JsonProperty(access = JsonProperty.Access.READ_ONLY)
	@Enumerated(EnumType.STRING)
	private StatusEntrega status;

	@JsonProperty(access = JsonProperty.Access.READ_ONLY)
	private LocalDateTime dataPedido;

	@JsonProperty(access = JsonProperty.Access.READ_ONLY)
	private LocalDateTime dataFinalizacao;

    public void finalizar() {
    	if(naoPodeSerFinalizado()) {
			throw new NegocioException("Entrega não pode ser finalizada!");
		}
    	setStatus(StatusEntrega.FINALIZADA);
    	setDataFinalizacao(LocalDateTime.now());
    }

    public boolean podeSerFinalizada() {
    	return StatusEntrega.PENDENTE.equals(getStatus());
	}

	public boolean naoPodeSerFinalizado() {
    	return !podeSerFinalizada();
	}

	public Ocorrencia aicionarOcorrencia(String descricao) {
    	Ocorrencia ocorrencia = new Ocorrencia();

    	ocorrencia.setDescricao(descricao);
    	ocorrencia.setDataRegistro(LocalDateTime.now());
    	ocorrencia.setEntrega(this);

    	this.getOcorrencias().add(ocorrencia);

    	return ocorrencia;
	}
}
