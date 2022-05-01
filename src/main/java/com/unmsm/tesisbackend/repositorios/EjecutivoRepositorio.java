package com.unmsm.tesisbackend.repositorios;


import org.springframework.data.jpa.repository.JpaRepository;

import com.unmsm.tesisbackend.entidades.Ejecutivo;

public interface EjecutivoRepositorio extends JpaRepository<Ejecutivo, Long>{
	
}