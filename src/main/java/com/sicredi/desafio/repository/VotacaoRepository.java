package com.sicredi.desafio.repository;

import com.sicredi.desafio.models.Votacao;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface VotacaoRepository extends JpaRepository<Votacao, Long> {

    List<Votacao> findByWeekOfYearYear(String weekOfYearYear);

    Optional<Votacao> findByData(String data);

}
