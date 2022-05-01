package com.unmsm.tesisbackend.repositorios;


import org.springframework.data.jpa.repository.JpaRepository;

import com.unmsm.tesisbackend.entidades.Procedencia;

public interface ProcedenciaRepositorio extends JpaRepository<Procedencia, Long>{
	
}