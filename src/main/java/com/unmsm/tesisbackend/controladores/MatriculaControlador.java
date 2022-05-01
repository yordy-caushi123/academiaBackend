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


import com.unmsm.tesisbackend.entidades.Matricula;
import com.unmsm.tesisbackend.excepciones.ResourceNotFoundException;
import com.unmsm.tesisbackend.repositorios.MatriculaRepositorio;


@RestController 
@CrossOrigin(origins = "${client.url}", maxAge=3600)
@RequestMapping("/matricula")
public class MatriculaControlador {
	@Autowired
    private MatriculaRepositorio matriculaRepositorio;
	
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	@GetMapping("/listar")
    public List<Matricula> listar() {
        return matriculaRepositorio.findAll();
    }

    @GetMapping("/recuperar/{id}")
    public ResponseEntity<Matricula> recuperarPorId(@PathVariable(value = "id") long id)
        throws ResourceNotFoundException {
    	Matricula matricula = matriculaRepositorio.findById(id).orElseThrow(() -> new ResourceNotFoundException("Matricula no encontrado para codTipodoc :: " + id));
        return ResponseEntity.ok().body(matricula);
    }
    
    @PostMapping("/registrar")
    public Matricula crear(@Valid @RequestBody Matricula matricula) {
        return matriculaRepositorio.save(matricula);
    }

    @PutMapping("/actualizar/{id}")
    public ResponseEntity<Matricula> actualizar(@PathVariable(value = "id") long id,
         @Valid @RequestBody Matricula detalle) throws ResourceNotFoundException {
        Matricula matricula = matriculaRepositorio.findById(id).orElseThrow(() -> new ResourceNotFoundException("Matricula no encontrado para id :: " + id));

        matricula=detalle;
        
        final Matricula actualizado = matriculaRepositorio.save(matricula);
        return ResponseEntity.ok(actualizado);
    } 
}