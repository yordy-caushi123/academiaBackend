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

import com.unmsm.tesisbackend.entidades.Mes;
import com.unmsm.tesisbackend.excepciones.ResourceNotFoundException;
import com.unmsm.tesisbackend.repositorios.MesRepositorio;


@RestController 
@CrossOrigin(origins = "${client.url}", maxAge=3600)
@RequestMapping("/mes")
public class MesControlador {
	@Autowired
    private MesRepositorio mesRepositorio;
	
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	@GetMapping("/listar")
    public List<Mes> listar() {
        return mesRepositorio.findAll();
    }

    @GetMapping("/recuperar/{id}")
    public ResponseEntity<Mes> recuperarPorId(@PathVariable(value = "id") long id)
        throws ResourceNotFoundException {
    	Mes mes = mesRepositorio.findById(id).orElseThrow(() -> new ResourceNotFoundException("Mes no encontrado para codTipodoc :: " + id));
        return ResponseEntity.ok().body(mes);
    }
    
    @PostMapping("/registrar")
    public Mes crear(@Valid @RequestBody Mes mes) {
        return mesRepositorio.save(mes);
    }

    @PutMapping("/actualizar/{id}")
    public ResponseEntity<Mes> actualizar(@PathVariable(value = "id") long id,
         @Valid @RequestBody Mes detalle) throws ResourceNotFoundException {
        Mes mes = mesRepositorio.findById(id).orElseThrow(() -> new ResourceNotFoundException("Referido no encontrado para id :: " + id));

        mes=detalle;
        
        final Mes actualizado = mesRepositorio.save(mes);
        return ResponseEntity.ok(actualizado);
    } 
}