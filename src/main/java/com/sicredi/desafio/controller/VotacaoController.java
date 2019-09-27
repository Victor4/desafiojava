package com.sicredi.desafio.controller;
import Utils.DateUtils;
import com.sicredi.desafio.models.Form.VotoForm;
import com.sicredi.desafio.models.Restaurante;
import com.sicredi.desafio.models.Usuario;
import com.sicredi.desafio.models.Votacao;
import com.sicredi.desafio.models.Voto;
import com.sicredi.desafio.repository.RestauranteRepository;
import com.sicredi.desafio.repository.UsuarioRepository;
import com.sicredi.desafio.repository.VotacaoRepository;
import com.sicredi.desafio.repository.VotoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;

@Controller
public class VotacaoController {

    @Autowired
    private VotacaoRepository votacaoRepository;

    @GetMapping("/resultados")
    @Transactional
    public String votacaoDetalhes(Model model){
        List<Votacao> votacao = votacaoRepository.findAll();
        verificarVencedoresDasVotacoes(votacao);
        model.addAttribute("votacao", votacao);
        return "votacao";
    }

    private void verificarVencedoresDasVotacoes(List<Votacao> votacoes){
        for(Votacao vot: votacoes){
            vot.verificarVencedor();
            votacaoRepository.save(vot);
        }
    }
}
