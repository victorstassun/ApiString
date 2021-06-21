package br.com.senai.domain.service;

import br.com.senai.api.assembler.PessoaAssembler;
import br.com.senai.api.model.PessoaModel;
import br.com.senai.domain.exception.NegocioException;
import br.com.senai.domain.exception.NegocioException;
import br.com.senai.domain.model.Pessoa;
import br.com.senai.domain.repository.PessoaRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@AllArgsConstructor
@Service
public class PessoaService {

    private PessoaRepository pessoaRepository;
    private PessoaAssembler pessoaAssembler;

    @Transactional
    public Pessoa cadastrar(Pessoa pessoa){
        boolean emailValdation = pessoaRepository.findByEmail(pessoa.getEmail()).isPresent();

        if (emailValdation){
            throw new NegocioException("Esse e-mail já foi cadastrado");
        }

        return pessoaRepository.save(pessoa);
    }

    public List<PessoaModel> listar(){
        return pessoaAssembler.toCollectionModel(pessoaRepository.findAll());
    }


    public List<PessoaModel> listarPorNome(String pessoaNome){
        return pessoaAssembler.toCollectionModel(pessoaRepository.findByNome(pessoaNome));
    }

    public Pessoa editarPessoa(Pessoa pessoa, long pessoaId) {
        pessoa.setId(pessoaId);
        pessoa = pessoaRepository.save(pessoa);
        return pessoa;
    }

    public List<PessoaModel> listarNomeQueContem(String pessoaNome){
        return pessoaAssembler.toCollectionModel(pessoaRepository.findByNomeContaining(pessoaNome));
    }

    @Transactional
    public void remover(Long pessoaId){
        pessoaRepository.deleteById(pessoaId);
    }

    public Pessoa buscar(Long pessoaId){
        return pessoaRepository.findById(pessoaId).orElseThrow(() -> new NegocioException("Pessoa Não econtrada"));
    }
}