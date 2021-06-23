package br.com.senai.api.model.input;

import br.com.senai.api.model.UsuarioDTO;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class PessoaInputDTO {

    @NotBlank
    private String nome;

    @NotNull
    private UsuarioDTO usuario;

    @NotBlank
    private String telefone;
}
