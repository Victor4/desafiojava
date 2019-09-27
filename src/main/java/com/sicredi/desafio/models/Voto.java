package com.sicredi.desafio.models;

import javax.persistence.*;

@Entity
public class Voto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private Restaurante restaurante;
    @ManyToOne
    private Usuario usuario;
    private Long votacao;

    public Voto(){}

    public Voto(Restaurante restaurante, Usuario usuario) {
        this.restaurante = restaurante;
        this.usuario = usuario;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Restaurante getRestaurante() {
        return restaurante;
    }

    public void setRestaurante(Restaurante restaurante) {
        this.restaurante = restaurante;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Long getVotacao() {
        return votacao;
    }

    public void setVotacao(Long votacao) {
        this.votacao = votacao;
    }
}
