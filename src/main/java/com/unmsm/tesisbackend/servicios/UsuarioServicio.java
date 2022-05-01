package com.unmsm.tesisbackend.servicios;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.unmsm.tesisbackend.entidades.Usuario;
import com.unmsm.tesisbackend.repositorios.UsuarioRepositorio;

import java.util.Optional;

@Service
@Transactional
public class UsuarioServicio {

    @Autowired
    UsuarioRepositorio usuarioRepository;

    public Optional<Usuario> getByNombreUsuario(String nu){
        return usuarioRepository.findByNombreUsuario(nu);
    }

    public void guardar(Usuario usuario){
        usuarioRepository.save(usuario);
    }
}
