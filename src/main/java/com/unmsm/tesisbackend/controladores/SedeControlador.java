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

import com.unmsm.tesisbackend.entidades.Sede;
import com.unmsm.tesisbackend.excepciones.ResourceNotFoundException;
import com.unmsm.tesisbackend.repositorios.SedeRepositorio;

@RestController 
@CrossOrigin(origins = "${client.url}", maxAge=3600)
@RequestMapping("/sede")
public class SedeControlador {
	@Autowired
    private SedeRepositorio sedeRepositorio;
	
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	@GetMapping("/listar")
    public List<Sede> listar() {
        return sedeRepositorio.findAll();
    }

    @GetMapping("/recuperar/{id}")
    public ResponseEntity<Sede> recuperarPorId(@PathVariable(value = "id") long id)
        throws ResourceNotFoundException {
    	Sede sede = sedeRepositorio.findById(id).orElseThrow(() -> new ResourceNotFoundException("Sede no encontrado para codTipodoc :: " + id));
        return ResponseEntity.ok().body(sede);
    }
    
    @PostMapping("/registrar")
    public Sede crear(@Valid @RequestBody Sede sede) {
        return sedeRepositorio.save(sede);
    }

    @PutMapping("/actualizar/{id}")
    public ResponseEntity<Sede> actualizar(@PathVariable(value = "id") long id,
         @Valid @RequestBody Sede detalle) throws ResourceNotFoundException {
        Sede sede = sedeRepositorio.findById(id).orElseThrow(() -> new ResourceNotFoundException("Sede no encontrado para id :: " + id));

        sede=detalle;
        
        final Sede actualizado = sedeRepositorio.save(sede);
        return ResponseEntity.ok(actualizado);
    } 
}