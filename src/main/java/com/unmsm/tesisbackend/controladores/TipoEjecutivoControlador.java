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

import com.unmsm.tesisbackend.entidades.TipoEjecutivo;
import com.unmsm.tesisbackend.excepciones.ResourceNotFoundException;
import com.unmsm.tesisbackend.repositorios.TipoEjecutivoRepositorio;


@RestController 
@CrossOrigin(origins = "${client.url}", maxAge=3600)
@RequestMapping("/tipoejecutivo")
public class TipoEjecutivoControlador {
	@Autowired
    private TipoEjecutivoRepositorio tipoejecutivoRepositorio;
	
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	@GetMapping("/listar")
    public List<TipoEjecutivo> listar() {
        return tipoejecutivoRepositorio.findAll();
    }

    @GetMapping("/recuperar/{id}")
    public ResponseEntity<TipoEjecutivo> recuperarPorId(@PathVariable(value = "id") long id)
        throws ResourceNotFoundException {
    	TipoEjecutivo tipoejecutivo = tipoejecutivoRepositorio.findById(id).orElseThrow(() -> new ResourceNotFoundException("TipoEjecutivo no encontrado para codTipodoc :: " + id));
        return ResponseEntity.ok().body(tipoejecutivo);
    }
    
    @PostMapping("/registrar")
    public TipoEjecutivo crear(@Valid @RequestBody TipoEjecutivo tipoejecutivo) {
        return tipoejecutivoRepositorio.save(tipoejecutivo);
    }

    @PutMapping("/actualizar/{id}")
    public ResponseEntity<TipoEjecutivo> actualizar(@PathVariable(value = "id") long id,
         @Valid @RequestBody TipoEjecutivo detalle) throws ResourceNotFoundException {
        TipoEjecutivo tipoejecutivo = tipoejecutivoRepositorio.findById(id).orElseThrow(() -> new ResourceNotFoundException("TipoEjecutivo no encontrado para id :: " + id));

        tipoejecutivo=detalle;
        
        final TipoEjecutivo actualizado = tipoejecutivoRepositorio.save(tipoejecutivo);
        return ResponseEntity.ok(actualizado);
    } 
}