package com.sicredi.desafio.models.DTO;

import com.sicredi.desafio.models.Voto;

public class VotoDto {

    private RestauranteDto restaurante;
    private UsuarioDto usuario;

    public VotoDto(){}

    public VotoDto(Voto voto) {
        this.restaurante = new RestauranteDto(voto.getRestaurante());
        this.usuario = new UsuarioDto(voto.getUsuario());
    }

    public RestauranteDto getRestaurante() {
        return restaurante;
    }

    public void setRestaurante(RestauranteDto restaurante) {
        this.restaurante = restaurante;
    }

    public UsuarioDto getUsuario() {
        return usuario;
    }

    public void setUsuario(UsuarioDto usuario) {
        this.usuario = usuario;
    }
}
