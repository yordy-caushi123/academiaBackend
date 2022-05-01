package com.unmsm.tesisbackend.repositorios;


import org.springframework.data.jpa.repository.JpaRepository;

import com.unmsm.tesisbackend.entidades.TipoEjecutivo;

public interface TipoEjecutivoRepositorio extends JpaRepository<TipoEjecutivo, Long>{
	
}