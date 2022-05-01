package com.unmsm.tesisbackend.repositorios;


import org.springframework.data.jpa.repository.JpaRepository;

import com.unmsm.tesisbackend.entidades.Sede;

public interface SedeRepositorio extends JpaRepository<Sede, Long>{
	
}