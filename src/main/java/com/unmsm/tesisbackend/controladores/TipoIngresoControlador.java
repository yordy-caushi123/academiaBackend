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



import com.unmsm.tesisbackend.entidades.TipoIngreso;
import com.unmsm.tesisbackend.excepciones.ResourceNotFoundException;
import com.unmsm.tesisbackend.repositorios.TipoIngresoRepositorio;




@RestController 
@CrossOrigin(origins = "${client.url}", maxAge=3600)
@RequestMapping("/tipoingreso")
public class TipoIngresoControlador {
	@Autowired
    private TipoIngresoRepositorio tipoingresoRepositorio;
	
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	@GetMapping("/listar")
    public List<TipoIngreso> listar() {
        return tipoingresoRepositorio.findAll();
    }

    @GetMapping("/recuperar/{id}")
    public ResponseEntity<TipoIngreso> recuperarPorId(@PathVariable(value = "id") long id)
        throws ResourceNotFoundException {
    	TipoIngreso tipoingreso = tipoingresoRepositorio.findById(id).orElseThrow(() -> new ResourceNotFoundException("TipoIngreso no encontrado para codTipodoc :: " + id));
        return ResponseEntity.ok().body(tipoingreso);
    }
    
    @PostMapping("/registrar")
    public TipoIngreso crear(@Valid @RequestBody TipoIngreso tipoingreso) {
        return tipoingresoRepositorio.save(tipoingreso);
    }

    @PutMapping("/actualizar/{id}")
    public ResponseEntity<TipoIngreso> actualizar(@PathVariable(value = "id") long id,
         @Valid @RequestBody TipoIngreso detalle) throws ResourceNotFoundException {
        TipoIngreso tipoingreso = tipoingresoRepositorio.findById(id).orElseThrow(() -> new ResourceNotFoundException("TipoIngreso no encontrado para id :: " + id));

        tipoingreso=detalle;
        
        final TipoIngreso actualizado = tipoingresoRepositorio.save(tipoingreso);
        return ResponseEntity.ok(actualizado);
    } 
}