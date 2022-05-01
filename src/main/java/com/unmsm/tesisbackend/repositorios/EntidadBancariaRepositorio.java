package com.unmsm.tesisbackend.repositorios;


import org.springframework.data.jpa.repository.JpaRepository;

import com.unmsm.tesisbackend.entidades.EntidadBancaria;

public interface EntidadBancariaRepositorio extends JpaRepository<EntidadBancaria, Long>{
	
}
