package br.com.senai.domain.service;

import br.com.senai.domain.exception.EntidadeNaoEcontradaException;
import br.com.senai.domain.exception.NegocioException;
import br.com.senai.domain.model.Entrega;
import br.com.senai.domain.repository.EntregaRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@AllArgsConstructor
@Service

public class EntregaService {

    private EntregaRepository entregaRepository;

    @Transactional
    public void finalizar(Long entregaId) {
        Entrega entrega = buscaEntrega(entregaId);

        entrega.finalizar();

        entregaRepository.save(entrega);
    }

    public Entrega buscaEntrega(long entregaId) {

        return entregaRepository.findById(entregaId)
                .orElseThrow(() -> new EntidadeNaoEcontradaException("Entrega não encontrada!"));
    }
}
