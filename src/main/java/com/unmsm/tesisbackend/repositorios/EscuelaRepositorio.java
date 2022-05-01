package com.unmsm.tesisbackend.repositorios;


import org.springframework.data.jpa.repository.JpaRepository;

import com.unmsm.tesisbackend.entidades.Escuela;

public interface EscuelaRepositorio extends JpaRepository<Escuela, Long>{
	
}