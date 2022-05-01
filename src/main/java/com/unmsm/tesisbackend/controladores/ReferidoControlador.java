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


import com.unmsm.tesisbackend.entidades.Referido;
import com.unmsm.tesisbackend.excepciones.ResourceNotFoundException;
import com.unmsm.tesisbackend.repositorios.ReferidoRepositorio;

@RestController 
@CrossOrigin(origins = "${client.url}", maxAge=3600)
@RequestMapping("/referido")
public class ReferidoControlador {
	@Autowired
    private ReferidoRepositorio referidoRepositorio;
	
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	@GetMapping("/listar")
    public List<Referido> listar() {
        return referidoRepositorio.findAll();
    }

    @GetMapping("/recuperar/{id}")
    public ResponseEntity<Referido> recuperarPorId(@PathVariable(value = "id") long id)
        throws ResourceNotFoundException {
    	Referido referido = referidoRepositorio.findById(id).orElseThrow(() -> new ResourceNotFoundException("Referido no encontrado para codTipodoc :: " + id));
        return ResponseEntity.ok().body(referido);
    }
    
    @PostMapping("/registrar")
    public Referido crear(@Valid @RequestBody Referido referido) {
        return referidoRepositorio.save(referido);
    }

    @PutMapping("/actualizar/{id}")
    public ResponseEntity<Referido> actualizar(@PathVariable(value = "id") long id,
         @Valid @RequestBody Referido detalle) throws ResourceNotFoundException {
        Referido referido = referidoRepositorio.findById(id).orElseThrow(() -> new ResourceNotFoundException("Referido no encontrado para id :: " + id));

        referido=detalle;
        
        final Referido actualizado = referidoRepositorio.save(referido);
        return ResponseEntity.ok(actualizado);
    } 
}