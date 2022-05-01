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

import com.unmsm.tesisbackend.entidades.Modalidad;
import com.unmsm.tesisbackend.excepciones.ResourceNotFoundException;
import com.unmsm.tesisbackend.repositorios.ModalidadRepositorio;

@RestController 
@CrossOrigin(origins = "${client.url}", maxAge=3600)
@RequestMapping("/modalidad")
public class ModalidadControlador {
	@Autowired
    private ModalidadRepositorio modalidadRepositorio;
	
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	@GetMapping("/listar")
    public List<Modalidad> listar() {
        return modalidadRepositorio.findAll();
    }

    @GetMapping("/recuperar/{id}")
    public ResponseEntity<Modalidad> recuperarPorId(@PathVariable(value = "id") long id)
        throws ResourceNotFoundException {
    	Modalidad modalidad = modalidadRepositorio.findById(id).orElseThrow(() -> new ResourceNotFoundException("Modalidad no encontrado para codTipodoc :: " + id));
        return ResponseEntity.ok().body(modalidad);
    }
    
    @PostMapping("/registrar")
    public Modalidad crear(@Valid @RequestBody Modalidad modalidad) {
        return modalidadRepositorio.save(modalidad);
    }

    @PutMapping("/actualizar/{id}")
    public ResponseEntity<Modalidad> actualizar(@PathVariable(value = "id") long id,
         @Valid @RequestBody Modalidad detalle) throws ResourceNotFoundException {
        Modalidad modalidad = modalidadRepositorio.findById(id).orElseThrow(() -> new ResourceNotFoundException("Modalidad no encontrado para id :: " + id));

        modalidad=detalle;
        
        final Modalidad actualizado = modalidadRepositorio.save(modalidad);
        return ResponseEntity.ok(actualizado);
    } 
}