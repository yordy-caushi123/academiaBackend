package com.unmsm.tesisbackend.controladores;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.unmsm.tesisbackend.entidades.EntidadBancaria;
import com.unmsm.tesisbackend.excepciones.ResourceNotFoundException;
import com.unmsm.tesisbackend.repositorios.EntidadBancariaRepositorio;





@RestController 
@CrossOrigin(origins = "${client.url}", maxAge=3600)
@RequestMapping("/entidadbancaria")
public class EntidadBancariaControlador {
	@Autowired
    private EntidadBancariaRepositorio entidadbancariaRepositorio;
	
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	@GetMapping("/listar")
    public List<EntidadBancaria> listar() {
        return entidadbancariaRepositorio.findAll();
    }

    @GetMapping("/recuperar/{id}")
    public ResponseEntity<EntidadBancaria> recuperarPorId(@PathVariable(value = "id") long id)
        throws ResourceNotFoundException {
    	EntidadBancaria entidadbancaria = entidadbancariaRepositorio.findById(id).orElseThrow(() -> new ResourceNotFoundException("EntidadBancaria no encontrado para codTipodoc :: " + id));
        return ResponseEntity.ok().body(entidadbancaria);
    }
    
    @PostMapping("/registrar")
    public EntidadBancaria crear(@Valid @RequestBody EntidadBancaria entidadbancaria) {
        return entidadbancariaRepositorio.save(entidadbancaria);
    }

    @PutMapping("/actualizar/{id}")
    public ResponseEntity<EntidadBancaria> actualizar(@PathVariable(value = "id") long id,
         @Valid @RequestBody EntidadBancaria detalle) throws ResourceNotFoundException {
        EntidadBancaria entidadbancaria = entidadbancariaRepositorio.findById(id).orElseThrow(() -> new ResourceNotFoundException("EntidadBancaria no encontrado para id :: " + id));

        entidadbancaria=detalle;
        
        final EntidadBancaria actualizado = entidadbancariaRepositorio.save(entidadbancaria);
        return ResponseEntity.ok(actualizado);
    } 
}