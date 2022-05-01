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


import com.unmsm.tesisbackend.entidades.TurnoModalidad;
import com.unmsm.tesisbackend.excepciones.ResourceNotFoundException;
import com.unmsm.tesisbackend.repositorios.TurnoModalidadRepositorio;

@RestController 
@CrossOrigin(origins = "${client.url}", maxAge=3600)
@RequestMapping("/turnomodalidad")
public class TurnoModalidadControlador {
	@Autowired
    private TurnoModalidadRepositorio turnomodalidadRepositorio;
	
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	@GetMapping("/listar")
    public List<TurnoModalidad> listar() {
        return turnomodalidadRepositorio.findAll();
    }

    @GetMapping("/recuperar/{id}")
    public ResponseEntity<TurnoModalidad> recuperarPorId(@PathVariable(value = "id") long id)
        throws ResourceNotFoundException {
    	TurnoModalidad turnomodalidad = turnomodalidadRepositorio.findById(id).orElseThrow(() -> new ResourceNotFoundException("TurnoModalidad no encontrado para codTipodoc :: " + id));
        return ResponseEntity.ok().body(turnomodalidad);
    }
    
    @PostMapping("/registrar")
    public TurnoModalidad crear(@Valid @RequestBody TurnoModalidad turnomodalidad) {
        return turnomodalidadRepositorio.save(turnomodalidad);
    }

    @PutMapping("/actualizar/{id}")
    public ResponseEntity<TurnoModalidad> actualizar(@PathVariable(value = "id") long id,
         @Valid @RequestBody TurnoModalidad detalle) throws ResourceNotFoundException {
        TurnoModalidad turnomodalidad = turnomodalidadRepositorio.findById(id).orElseThrow(() -> new ResourceNotFoundException("TurnoModalidad no encontrado para id :: " + id));

        turnomodalidad=detalle;
        
        final TurnoModalidad actualizado = turnomodalidadRepositorio.save(turnomodalidad);
        return ResponseEntity.ok(actualizado);
    } 
}