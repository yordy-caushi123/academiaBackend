package com.unmsm.tesisbackend.repositorios;


import org.springframework.data.jpa.repository.JpaRepository;

import com.unmsm.tesisbackend.entidades.Egreso;

public interface EgresoRepositorio extends JpaRepository<Egreso, Long>{
	
}
