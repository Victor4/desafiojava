package com.sicredi.desafio.models.DTO;

import com.sicredi.desafio.models.Restaurante;


public class RestauranteDto {
    private String nome;
    private String localizacao;

    public RestauranteDto(Restaurante restaurante) {
        this.nome = restaurante.getNome();
        this.localizacao = restaurante.getLocalizacao();
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getLocalizacao() {
        return localizacao;
    }

    public void setLocalizacao(String localizacao) {
        this.localizacao = localizacao;
    }
}
