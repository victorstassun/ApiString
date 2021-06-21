package br.com.senai.api.model.input;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class PessoaInput {

    private String nome;
    private String email;
    private String telefone;
}
