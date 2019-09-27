package com.sicredi.desafio.models.DTO;

import com.sicredi.desafio.models.Usuario;

public class UsuarioDto {

    private String nome;

    public UsuarioDto() {}

    public UsuarioDto(Usuario usuario) {
        this.nome = usuario.getNome();
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

}
