package com.sicredi.desafio.repository;

import com.sicredi.desafio.models.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

}
