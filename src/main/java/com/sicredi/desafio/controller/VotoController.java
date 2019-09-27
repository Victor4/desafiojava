package com.sicredi.desafio.controller;

import Utils.DateUtils;
import com.sicredi.desafio.models.DTO.VotoDto;
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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.util.UriComponentsBuilder;
import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;

@Controller("/voto")
public class VotoController {

    @Autowired
    private RestauranteRepository restauranteRepository;

    @Autowired
    private VotacaoRepository votacaoRepository;

    @Autowired
    private VotoRepository votoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @GetMapping
    public String votar(Model model){
        List<Restaurante> restaurantes = listaComRestaurantesQueNaoVenceramNaSemana();
        List<Usuario> usuarios = usuarioRepository.findAll();
        model.addAttribute("dataAtual", DateUtils.dataAtual());
        model.addAttribute("votoForm", new VotoForm());
        model.addAttribute("usuarios", usuarios);
        model.addAttribute("restaurantes", restaurantes);
        return "home";
    }

    private List<Restaurante> listaComRestaurantesQueNaoVenceramNaSemana() {
        List<Votacao> votacao = votacaoRepository.findByWeekOfYearYear(DateUtils.chaveSemanaAno());
        List<Restaurante> restaurantes = restauranteRepository.findAll();
        List<Restaurante> restaurantesAux = restauranteRepository.findAll();
        for (Votacao vot: votacao) {
            for (Restaurante rest: restaurantes ) {
                if(vot.getVencedor()!=null) {
                    if (vot.getVencedor().getId() == rest.getId()) {
                        restaurantesAux.remove(rest);
                        continue;
                    }
                }
            }

        }
        return restaurantesAux;
    }

    @PostMapping
    @Transactional
    @ResponseBody
    public ResponseEntity<VotoDto> cadastrar(@Valid VotoForm votoForm, UriComponentsBuilder uriBuilder){
        Voto voto = buscarUsuarioERestaurante(votoForm);
        if(voto == null) {
            return ResponseEntity.badRequest().build();
        }
        Optional<Votacao> votacao = votacaoRepository.findByData(DateUtils.dataAtual());

        if(votacao.isPresent()){
            if(verificarSeUsuarioJaVotou(votacao.get(),voto)){
                return  ResponseEntity.badRequest().build();
            }
            adicionarVotoNaVotacao(voto,votacao.get());
        }else{
            Votacao novaVotacao = new Votacao(DateUtils.dataAtual(),DateUtils.chaveSemanaAno());
            adicionarVotoNaVotacao(voto, novaVotacao);
        }

        VotoDto votoDto = new VotoDto(voto);
        URI uri = uriBuilder.path("/voto/{id}").buildAndExpand(voto.getId()).toUri();
        return  ResponseEntity.created(uri).body(votoDto);
    }

    private Voto buscarUsuarioERestaurante(VotoForm votoForm) {
        Optional<Restaurante> restaurante = restauranteRepository.findById(votoForm.getRestaurante());
        Optional<Usuario> usuario = usuarioRepository.findById(votoForm.getUsuario());
        if(verificacaoRestauranteEUsuarioExistem(restaurante,usuario)) {
            return null;
        }else{
            return new Voto(restaurante.get(),usuario.get());
        }
    }

    private boolean verificarSeUsuarioJaVotou(Votacao votacao, Voto voto) {
        for(Voto v: votacao.getVotos()){
            if(v.getUsuario().getId() == voto.getUsuario().getId()){
                return true;
            }
        }
        return false;
    }

    private void adicionarVotoNaVotacao(Voto voto, Votacao votacao) {
        votacao.getVotos().add(voto);
        votacaoRepository.save(votacao);
        voto.setVotacao(votacao.getId());
        votoRepository.save(voto);
    }

    private boolean verificacaoRestauranteEUsuarioExistem(Optional<Restaurante> restaurante, Optional<Usuario> usuario) {
        return !restaurante.isPresent() || !usuario.isPresent();
    }

}
