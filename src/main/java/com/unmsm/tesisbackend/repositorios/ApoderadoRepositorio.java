package com.unmsm.tesisbackend.repositorios;


import org.springframework.data.jpa.repository.JpaRepository;

import com.unmsm.tesisbackend.entidades.Apoderado;

public interface ApoderadoRepositorio extends JpaRepository<Apoderado, Long>{
	
}