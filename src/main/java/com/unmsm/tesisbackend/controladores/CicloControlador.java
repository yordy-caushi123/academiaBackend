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

import com.unmsm.tesisbackend.entidades.Ciclo;
import com.unmsm.tesisbackend.excepciones.ResourceNotFoundException;
import com.unmsm.tesisbackend.repositorios.CicloRepositorio;

@RestController 
@CrossOrigin(origins = "${client.url}", maxAge=3600)
@RequestMapping("/ciclo")
public class CicloControlador {
	@Autowired
    private CicloRepositorio cicloRepositorio;
	
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	@GetMapping("/listar")
    public List<Ciclo> listar() {
        return cicloRepositorio.findAll();
    }

    @GetMapping("/recuperar/{id}")
    public ResponseEntity<Ciclo> recuperarPorId(@PathVariable(value = "id") long id)
        throws ResourceNotFoundException {
    	Ciclo ciclo = cicloRepositorio.findById(id).orElseThrow(() -> new ResourceNotFoundException("Ciclo no encontrado para codTipodoc :: " + id));
        return ResponseEntity.ok().body(ciclo);
    }
    
    @PostMapping("/registrar")
    public Ciclo crear(@Valid @RequestBody Ciclo ciclo) {
        return cicloRepositorio.save(ciclo);
    }

    @PutMapping("/actualizar/{id}")
    public ResponseEntity<Ciclo> actualizar(@PathVariable(value = "id") long id,
         @Valid @RequestBody Ciclo detalle) throws ResourceNotFoundException {
        Ciclo ciclo = cicloRepositorio.findById(id).orElseThrow(() -> new ResourceNotFoundException("Ciclo no encontrado para id :: " + id));

        ciclo=detalle;
        
        final Ciclo actualizado = cicloRepositorio.save(ciclo);
        return ResponseEntity.ok(actualizado);
    } 
}