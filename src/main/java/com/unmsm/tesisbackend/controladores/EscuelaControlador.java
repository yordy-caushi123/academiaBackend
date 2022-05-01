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

import com.unmsm.tesisbackend.entidades.Escuela;
import com.unmsm.tesisbackend.excepciones.ResourceNotFoundException;
import com.unmsm.tesisbackend.repositorios.EscuelaRepositorio;

@RestController 
@CrossOrigin(origins = "${client.url}", maxAge=3600)
@RequestMapping("/escuela")
public class EscuelaControlador {
	@Autowired
    private EscuelaRepositorio escuelaRepositorio;
	
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	@GetMapping("/listar")
    public List<Escuela> listar() {
        return escuelaRepositorio.findAll();
    }

    @GetMapping("/recuperar/{id}")
    public ResponseEntity<Escuela> recuperarPorId(@PathVariable(value = "id") long id)
        throws ResourceNotFoundException {
    	Escuela escuela = escuelaRepositorio.findById(id).orElseThrow(() -> new ResourceNotFoundException("Escuela no encontrado para codTipodoc :: " + id));
        return ResponseEntity.ok().body(escuela);
    }
    
    @PostMapping("/registrar")
    public Escuela crear(@Valid @RequestBody Escuela escuela) {
        return escuelaRepositorio.save(escuela);
    }

    @PutMapping("/actualizar/{id}")
    public ResponseEntity<Escuela> actualizar(@PathVariable(value = "id") long id,
         @Valid @RequestBody Escuela detalle) throws ResourceNotFoundException {
        Escuela escuela = escuelaRepositorio.findById(id).orElseThrow(() -> new ResourceNotFoundException("Escuela no encontrado para id :: " + id));

        escuela=detalle;
        
        final Escuela actualizado = escuelaRepositorio.save(escuela);
        return ResponseEntity.ok(actualizado);
    } 
}