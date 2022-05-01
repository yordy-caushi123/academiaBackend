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

import com.unmsm.tesisbackend.entidades.Procedencia;
import com.unmsm.tesisbackend.excepciones.ResourceNotFoundException;
import com.unmsm.tesisbackend.repositorios.ProcedenciaRepositorio;

@RestController 
@CrossOrigin(origins = "${client.url}", maxAge=3600)
@RequestMapping("/procedencia")
public class ProcedenciaControlador {
	@Autowired
    private ProcedenciaRepositorio procedenciaRepositorio;
	
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	@GetMapping("/listar")
    public List<Procedencia> listar() {
        return procedenciaRepositorio.findAll();
    }

    @GetMapping("/recuperar/{id}")
    public ResponseEntity<Procedencia> recuperarPorId(@PathVariable(value = "id") long id)
        throws ResourceNotFoundException {
    	Procedencia procedencia = procedenciaRepositorio.findById(id).orElseThrow(() -> new ResourceNotFoundException("Procedencia no encontrado para codTipodoc :: " + id));
        return ResponseEntity.ok().body(procedencia);
    }
    
    @PostMapping("/registrar")
    public Procedencia crear(@Valid @RequestBody Procedencia procedencia) {
        return procedenciaRepositorio.save(procedencia);
    }

    @PutMapping("/actualizar/{id}")
    public ResponseEntity<Procedencia> actualizar(@PathVariable(value = "id") long id,
         @Valid @RequestBody Procedencia detalle) throws ResourceNotFoundException {
        Procedencia procedencia = procedenciaRepositorio.findById(id).orElseThrow(() -> new ResourceNotFoundException("Procedencia no encontrado para id :: " + id));

        procedencia=detalle;
        
        final Procedencia actualizado = procedenciaRepositorio.save(procedencia);
        return ResponseEntity.ok(actualizado);
    } 
}