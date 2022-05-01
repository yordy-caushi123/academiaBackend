package com.unmsm.tesisbackend.repositorios;


import org.springframework.data.jpa.repository.JpaRepository;

import com.unmsm.tesisbackend.entidades.Referido;

public interface ReferidoRepositorio extends JpaRepository<Referido, Long>{
	
}