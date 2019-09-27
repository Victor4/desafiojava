package com.sicredi.desafio.models;

import javax.persistence.*;
import java.util.*;

@Entity
public class Votacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String data;
    private String weekOfYearYear;
    @ManyToOne
    private Restaurante vencedor;
    @OneToMany(mappedBy = "votacao")
    private List<Voto> votos = new ArrayList<>();

    public Votacao() {}

    public Votacao(String data, String weekOfYearYear) {
        this.data = data;
        this.weekOfYearYear = weekOfYearYear;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public Restaurante getVencedor() {
        return vencedor;
    }

    public void setVencedor(Restaurante vencedor) {
        this.vencedor = vencedor;
    }

    public List<Voto> getVotos() {
        return votos;
    }

    public void setVotos(List<Voto> votos) {
        this.votos = votos;
    }

    public String getWeekOfYearYear() {
        return weekOfYearYear;
    }

    public void setWeekOfYearYear(String weekOfYearYear) {
        this.weekOfYearYear = weekOfYearYear;
    }

    public List<Restaurante> verificarVencedor(){
        HashMap<Restaurante,Integer> maiorPontuadores = new HashMap<>();
        List<Restaurante> vencedores = new ArrayList<>();
        Integer maiorQuantidadeVotos = 0;
        for (Voto voto: votos) {
            if(maiorPontuadores.containsKey(voto.getRestaurante())){
                int somaVotos = maiorPontuadores.get(voto.getRestaurante());
                maiorPontuadores.put(voto.getRestaurante(),somaVotos+1);
                if(somaVotos+1>maiorQuantidadeVotos){
                    maiorQuantidadeVotos = somaVotos+1;
                }
            }else{
                maiorPontuadores.put(voto.getRestaurante(),1);
                if(1 > maiorQuantidadeVotos){
                    maiorQuantidadeVotos = 1;
                }
            }
        }
        for(Map.Entry<Restaurante,Integer> pair : maiorPontuadores.entrySet()){
            if(pair.getValue() == maiorQuantidadeVotos){
                vencedores.add(pair.getKey());
            }
        }
        if(vencedores.size()>0) {
            setVencedor(vencedores.get(0));
        }
        return vencedores;
    }
}
