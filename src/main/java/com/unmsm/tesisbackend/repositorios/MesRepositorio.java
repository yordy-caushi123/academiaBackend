package com.unmsm.tesisbackend.repositorios;


import org.springframework.data.jpa.repository.JpaRepository;

import com.unmsm.tesisbackend.entidades.Mes;

public interface MesRepositorio extends JpaRepository<Mes, Long>{
	
}