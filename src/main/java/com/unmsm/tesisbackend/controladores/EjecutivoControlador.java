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

import com.unmsm.tesisbackend.entidades.Ejecutivo;
import com.unmsm.tesisbackend.excepciones.ResourceNotFoundException;
import com.unmsm.tesisbackend.repositorios.EjecutivoRepositorio;


@RestController 
@CrossOrigin(origins = "${client.url}", maxAge=3600)
@RequestMapping("/ejecutivo")
public class EjecutivoControlador {
	@Autowired
    private EjecutivoRepositorio ejecutivoRepositorio;
	
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	@GetMapping("/listar")
    public List<Ejecutivo> listar() {
        return ejecutivoRepositorio.findAll();
    }

    @GetMapping("/recuperar/{id}")
    public ResponseEntity<Ejecutivo> recuperarPorId(@PathVariable(value = "id") long id)
        throws ResourceNotFoundException {
    	Ejecutivo ejecutivo = ejecutivoRepositorio.findById(id).orElseThrow(() -> new ResourceNotFoundException("Ejecutivo no encontrado para codTipodoc :: " + id));
        return ResponseEntity.ok().body(ejecutivo);
    }
    
    @PostMapping("/registrar")
    public Ejecutivo crear(@Valid @RequestBody Ejecutivo ejecutivo) {
        return ejecutivoRepositorio.save(ejecutivo);
    }

    @PutMapping("/actualizar/{id}")
    public ResponseEntity<Ejecutivo> actualizar(@PathVariable(value = "id") long id,
         @Valid @RequestBody Ejecutivo detalle) throws ResourceNotFoundException {
        Ejecutivo ejecutivo = ejecutivoRepositorio.findById(id).orElseThrow(() -> new ResourceNotFoundException("Ejecutivo no encontrado para id :: " + id));

        ejecutivo=detalle;
        
        final Ejecutivo actualizado = ejecutivoRepositorio.save(ejecutivo);
        return ResponseEntity.ok(actualizado);
    } 
}