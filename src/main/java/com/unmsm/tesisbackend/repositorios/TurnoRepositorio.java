package com.unmsm.tesisbackend.repositorios;


import org.springframework.data.jpa.repository.JpaRepository;

import com.unmsm.tesisbackend.entidades.Turno;

public interface TurnoRepositorio extends JpaRepository<Turno, Long>{
	
}