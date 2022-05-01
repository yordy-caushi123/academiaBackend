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

import com.unmsm.tesisbackend.entidades.Apoderado;
import com.unmsm.tesisbackend.excepciones.ResourceNotFoundException;
import com.unmsm.tesisbackend.repositorios.ApoderadoRepositorio;



@RestController 
@CrossOrigin(origins = "${client.url}", maxAge=3600)
@RequestMapping("/apoderado")
public class ApoderadoControlador {
	@Autowired
    private ApoderadoRepositorio apoderadoRepositorio;
	
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	@GetMapping("/listar")
    public List<Apoderado> listar() {
        return apoderadoRepositorio.findAll();
    }

    @GetMapping("/recuperar/{id}")
    public ResponseEntity<Apoderado> recuperarPorId(@PathVariable(value = "id") long id)
        throws ResourceNotFoundException {
    	Apoderado apoderado = apoderadoRepositorio.findById(id).orElseThrow(() -> new ResourceNotFoundException("Apoderado no encontrado para codTipodoc :: " + id));
        return ResponseEntity.ok().body(apoderado);
    }
    
    @PostMapping("/registrar")
    public Apoderado crear(@Valid @RequestBody Apoderado apoderado) {
        return apoderadoRepositorio.save(apoderado);
    }

    @PutMapping("/actualizar/{id}")
    public ResponseEntity<Apoderado> actualizar(@PathVariable(value = "id") long id,
         @Valid @RequestBody Apoderado detalle) throws ResourceNotFoundException {
        Apoderado apoderado = apoderadoRepositorio.findById(id).orElseThrow(() -> new ResourceNotFoundException("Apoderado no encontrado para id :: " + id));

        apoderado=detalle;
        
        final Apoderado actualizado = apoderadoRepositorio.save(apoderado);
        return ResponseEntity.ok(actualizado);
    } 
}