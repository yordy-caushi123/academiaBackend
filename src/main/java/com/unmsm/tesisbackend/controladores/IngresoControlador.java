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


import com.unmsm.tesisbackend.entidades.Ingreso;
import com.unmsm.tesisbackend.excepciones.ResourceNotFoundException;
import com.unmsm.tesisbackend.repositorios.IngresoRepositorio;





@RestController 
@CrossOrigin(origins = "${client.url}", maxAge=3600)
@RequestMapping("/ingreso")
public class IngresoControlador {
	@Autowired
    private IngresoRepositorio ingresoRepositorio;
	
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	@GetMapping("/listar")
    public List<Ingreso> listar() {
        return ingresoRepositorio.findAll();
    }

    @GetMapping("/recuperar/{id}")
    public ResponseEntity<Ingreso> recuperarPorId(@PathVariable(value = "id") long id)
        throws ResourceNotFoundException {
    	Ingreso ingreso = ingresoRepositorio.findById(id).orElseThrow(() -> new ResourceNotFoundException("Ingreso no encontrado para codTipodoc :: " + id));
        return ResponseEntity.ok().body(ingreso);
    }
    
    @PostMapping("/registrar")
    public Ingreso crear(@Valid @RequestBody Ingreso ingreso) {
        return ingresoRepositorio.save(ingreso);
    }

    @PutMapping("/actualizar/{id}")
    public ResponseEntity<Ingreso> actualizar(@PathVariable(value = "id") long id,
         @Valid @RequestBody Ingreso detalle) throws ResourceNotFoundException {
        Ingreso ingreso = ingresoRepositorio.findById(id).orElseThrow(() -> new ResourceNotFoundException("Ingreso no encontrado para id :: " + id));

        ingreso=detalle;
        
        final Ingreso actualizado = ingresoRepositorio.save(ingreso);
        return ResponseEntity.ok(actualizado);
    } 
}