package br.com.senai.api.controller;

import br.com.senai.api.assembler.PessoaAssembler;
import br.com.senai.api.model.PessoaDTO;
import br.com.senai.api.model.input.PessoaInputDTO;
import br.com.senai.domain.model.Pessoa;
import br.com.senai.domain.repository.PessoaRepository;
import br.com.senai.domain.service.PessoaService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/pessoas")
public class PessoaController {

    private PessoaRepository pessoaRepository;
    private PessoaService pessoaService;
    private PessoaAssembler pessoaAssembler;


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PessoaDTO cadastrar(@Valid @RequestBody PessoaInputDTO pessoaInput){

        Pessoa novaPessoa = pessoaAssembler.toEntity(pessoaInput);
        novaPessoa.getUsuario().setSenha(
                new BCryptPasswordEncoder()
                        .encode(pessoaInput.getUsuario().getSenha()));
        Pessoa pessoa = pessoaService.cadastrar(novaPessoa);

        return pessoaAssembler.toModel(pessoa);
    }

    @GetMapping
    public List<PessoaDTO> listar(){
        return pessoaService.listar();
    }


    @GetMapping("/nome/{pessoaNome}")
    public List<PessoaDTO> listarPorNome(@PathVariable String pessoaNome){
        return pessoaService.listarPorNome(pessoaNome);
    }


    @GetMapping("/nome/containing/{nomeContaining}")
    public List<PessoaDTO> listarNomeContaining(@PathVariable String nomeContaining){
        return pessoaService.listarNomeQueContem(nomeContaining);
    }


    @GetMapping("{pessoaId}")
    public Pessoa buscar(@PathVariable Long pessoaId) {
        return pessoaService.buscar(pessoaId);
    }

    @PutMapping("/{pessoaId}")
    public ResponseEntity<PessoaDTO> editarPessoa(
            @Valid @PathVariable long pessoaId,
            @RequestBody PessoaInputDTO pessoaInput
    ){
        if (!pessoaRepository.existsById(pessoaId)) {
            return ResponseEntity.notFound().build();
        }

        Pessoa novaPessoa = pessoaAssembler.toEntity(pessoaInput);
        Pessoa pessoa = pessoaService.editarPessoa(novaPessoa, pessoaId);

        return ResponseEntity.ok(pessoaAssembler.toModel(pessoa));
    }


    @DeleteMapping("/{pessoaId}")
    public ResponseEntity<Pessoa> remover(@PathVariable Long pessoaId){
        if (!pessoaRepository.existsById(pessoaId)){
            return  ResponseEntity.notFound().build();
        }
        pessoaService.remover(pessoaId);

        return ResponseEntity.noContent().build();
    }
}
