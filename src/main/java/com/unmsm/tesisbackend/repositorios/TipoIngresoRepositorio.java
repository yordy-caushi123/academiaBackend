package com.unmsm.tesisbackend.repositorios;


import org.springframework.data.jpa.repository.JpaRepository;

import com.unmsm.tesisbackend.entidades.TipoIngreso;

public interface TipoIngresoRepositorio extends JpaRepository<TipoIngreso, Long>{
	
}
