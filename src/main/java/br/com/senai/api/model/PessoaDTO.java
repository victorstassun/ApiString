package br.com.senai.api.model;

import lombok.Getter;
import lombok.Setter;

import java.math.BigInteger;

@Getter
@Setter
public class PessoaDTO {

    private Long id;
    private String nome;
    private UsuarioDTO usuario;
    private String telefone;

}
