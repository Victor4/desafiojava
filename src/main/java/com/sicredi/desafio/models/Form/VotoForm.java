package com.sicredi.desafio.models.Form;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class VotoForm {
    @NotNull
    private Long restaurante;
    @NotNull
    private Long usuario;

    public VotoForm() {
    }

    public VotoForm(@NotNull Long restaurante, @NotNull Long usuario) {
        this.restaurante = restaurante;
        this.usuario = usuario;
    }

    public Long getRestaurante() {
        return restaurante;
    }

    public void setRestaurante(Long restaurante) {
        this.restaurante = restaurante;
    }

    public Long getUsuario() {
        return usuario;
    }

    public void setUsuario(Long usuario) {
        this.usuario = usuario;
    }
}
