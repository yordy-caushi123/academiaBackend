package com.unmsm.tesisbackend.controladores;

import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.unmsm.tesisbackend.dto.JwtDTO;
import com.unmsm.tesisbackend.dto.LoginUsuario;
import com.unmsm.tesisbackend.dto.Mensaje;
import com.unmsm.tesisbackend.dto.NuevoUsuario;
import com.unmsm.tesisbackend.entidades.Rol;
import com.unmsm.tesisbackend.entidades.Usuario;
import com.unmsm.tesisbackend.enumeraciones.RolNombre;
import com.unmsm.tesisbackend.seguridad.jwt.JwtProvider;
import com.unmsm.tesisbackend.servicios.RolServicio;
import com.unmsm.tesisbackend.servicios.UsuarioServicio;

import jcifs.smb.SmbException;
import javax.validation.Valid;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@RestController
@CrossOrigin(origins = "${client.url}", maxAge=3600)
@RequestMapping("/auth")
public class AuthControlador {

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UsuarioServicio usuarioService;

    @Autowired
    RolServicio rolService;

    @Autowired
    JwtProvider jwtProvider;
    
    @PostMapping("/nuevo")
    public ResponseEntity<?> nuevo(@Valid @RequestBody NuevoUsuario nuevoUsuario, BindingResult bindingResult) throws SmbException{
        Usuario usuario =
                new Usuario(
						nuevoUsuario.getTipoUsuario(),
						nuevoUsuario.getEstadoUsuario(),
						nuevoUsuario.getNombreUsuario(),
						passwordEncoder.encode(nuevoUsuario.getContrasenaUsuario()),
						nuevoUsuario.getIdUsuCreacion(),
						new Date(),
						nuevoUsuario.getIpCreacion()
						);
        
        Set<String> rolesStr = nuevoUsuario.getRoles();
        Set<Rol> roles = new HashSet<>();
        for (String rol : rolesStr) {
        	Rol rolAdmin =  null;
            switch (rol) {
                case "administrador":
                    rolAdmin = rolService.getByRolNombre(RolNombre.ROLE_ADMINISTRADOR).get();
                    roles.add(rolAdmin);
                    break;
                case "docente":
                    rolAdmin = rolService.getByRolNombre(RolNombre.ROLE_DOCENTE).get();
                    roles.add(rolAdmin);
                    break;
                case "alumno":
                    rolAdmin = rolService.getByRolNombre(RolNombre.ROLE_ALUMNO).get();
                    roles.add(rolAdmin);
                    break;
            }
        }
        usuario.setRoles(roles);
          
        usuarioService.guardar(usuario);
        //return new ResponseEntity(new Mensaje("usuario guardado"), HttpStatus.CREATED);
        return ResponseEntity.ok().body(usuario);
    }

    @PostMapping("/login")
    public ResponseEntity<JwtDTO> login(@Valid @RequestBody LoginUsuario loginUsuario, BindingResult bindingResult){
        if(bindingResult.hasErrors())
            return new ResponseEntity(new Mensaje("Usuario o contraseña incorrecta"), HttpStatus.BAD_REQUEST);
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginUsuario.getNombreUsuario(), loginUsuario.getPassword())
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtProvider.generateToken(authentication);
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        JwtDTO jwtDTO = new JwtDTO(jwt, userDetails.getUsername(), userDetails.getAuthorities());
        return new ResponseEntity<JwtDTO>(jwtDTO, HttpStatus.OK);
    }
    
   
}