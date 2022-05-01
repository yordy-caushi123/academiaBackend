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

import com.unmsm.tesisbackend.entidades.Turno;
import com.unmsm.tesisbackend.excepciones.ResourceNotFoundException;
import com.unmsm.tesisbackend.repositorios.TurnoRepositorio;

@RestController 
@CrossOrigin(origins = "${client.url}", maxAge=3600)
@RequestMapping("/turno")
public class TurnoControlador {
	@Autowired
    private TurnoRepositorio turnoRepositorio;
	
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	@GetMapping("/listar")
    public List<Turno> listar() {
        return turnoRepositorio.findAll();
    }

    @GetMapping("/recuperar/{id}")
    public ResponseEntity<Turno> recuperarPorId(@PathVariable(value = "id") long id)
        throws ResourceNotFoundException {
    	Turno turno = turnoRepositorio.findById(id).orElseThrow(() -> new ResourceNotFoundException("Turno no encontrado para codTipodoc :: " + id));
        return ResponseEntity.ok().body(turno);
    }
    
    @PostMapping("/registrar")
    public Turno crear(@Valid @RequestBody Turno turno) {
        return turnoRepositorio.save(turno);
    }

    @PutMapping("/actualizar/{id}")
    public ResponseEntity<Turno> actualizar(@PathVariable(value = "id") long id,
         @Valid @RequestBody Turno detalle) throws ResourceNotFoundException {
        Turno turno = turnoRepositorio.findById(id).orElseThrow(() -> new ResourceNotFoundException("Turno no encontrado para id :: " + id));

        turno=detalle;
        
        final Turno actualizado = turnoRepositorio.save(turno);
        return ResponseEntity.ok(actualizado);
    } 
}