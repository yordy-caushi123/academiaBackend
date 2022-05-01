package com.unmsm.tesisbackend.servicios;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.unmsm.tesisbackend.entidades.Rol;
import com.unmsm.tesisbackend.enumeraciones.RolNombre;
import com.unmsm.tesisbackend.repositorios.RolRepositorio;

import java.util.Optional;

@Service
@Transactional
public class RolServicio{

    @Autowired
    RolRepositorio rolRepository;

    public Optional<Rol> getByRolNombre(RolNombre rolNombre){
        return rolRepository.findByRolNombre(rolNombre);
    }
}
