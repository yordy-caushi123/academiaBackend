package com.unmsm.tesisbackend.repositorios;


import org.springframework.data.jpa.repository.JpaRepository;

import com.unmsm.tesisbackend.entidades.Matricula;

public interface MatriculaRepositorio extends JpaRepository<Matricula, Long>{
	
}