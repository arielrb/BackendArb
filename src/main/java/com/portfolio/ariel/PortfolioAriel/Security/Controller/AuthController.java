package com.portfolio.ariel.PortfolioAriel.Security.Controller;

import java.util.HashSet;
import java.util.Set;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.portfolio.ariel.PortfolioAriel.Entity.Usuario;
import com.portfolio.ariel.PortfolioAriel.Security.Entity.Rol;
import com.portfolio.ariel.PortfolioAriel.Security.Enums.RolNombre;
import com.portfolio.ariel.PortfolioAriel.Security.JWT.JwtProvider;
import com.portfolio.ariel.PortfolioAriel.Security.Service.RolService;
import com.portfolio.ariel.PortfolioAriel.Security.Service.UsuarioService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import com.portfolio.ariel.PortfolioAriel.Security.Controller.Mensaje;
import com.portfolio.ariel.PortfolioAriel.Security.DTO.JwtDTO;
import com.portfolio.ariel.PortfolioAriel.Security.DTO.LoginUsuario;
import com.portfolio.ariel.PortfolioAriel.Security.DTO.NuevoUsuario;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = {"https://frontendarb.web.app"})
public class AuthController {
	@Autowired
	PasswordEncoder passEncoder;
	@Autowired
	AuthenticationManager authManager;
	@Autowired
	UsuarioService usuarioServ;
	@Autowired
	RolService rolServ;
	@Autowired
	JwtProvider jwtProv;

	// Creo el usuario

	@PostMapping("/nuevo")
	public ResponseEntity<?> nuevo(@Valid @RequestBody NuevoUsuario nuevoUsuario, BindingResult bindResult) 
	{

		if (bindResult.hasErrors()) {
			return new ResponseEntity(new Mensaje("Campos incorrectos o email invalido"), HttpStatus.BAD_REQUEST);
		}
		if (usuarioServ.existByNombreUsuario(nuevoUsuario.getNombreUsuario())) {
			return new ResponseEntity(new Mensaje("Nombre de usuario ya existe"), HttpStatus.BAD_REQUEST);
		}
		if (usuarioServ.existByEmail(nuevoUsuario.getEmail())) {
			return new ResponseEntity(new Mensaje("Ese email ya existe"), HttpStatus.BAD_REQUEST);
		}
		Usuario usuario = new Usuario(nuevoUsuario.getNombre(), nuevoUsuario.getNombreUsuario(), nuevoUsuario.getEmail(),
				passEncoder.encode(nuevoUsuario.getContrasenia()));

		Set<Rol> roles = new HashSet<>();
		roles.add(rolServ.getByRolNombre(RolNombre.ROLE_USER).get());

		if (nuevoUsuario.getRoles().contains("admin")) {
			roles.add(rolServ.getByRolNombre(RolNombre.ROLE_ADMIN).get());
			usuario.setRoles(roles);
			usuarioServ.save(usuario);
		}

			return new ResponseEntity(new Mensaje("Usuario guardado!"), HttpStatus.CREATED);
		
	}
		//Login
		@PostMapping("/login")
		public ResponseEntity<JwtDTO> login(@RequestBody @Valid LoginUsuario loginUsuario, BindingResult bindingResult)
		{
		
			
			if(bindingResult.hasErrors()) {
				return new ResponseEntity(new Mensaje("Campos incorrectos!"),HttpStatus.BAD_REQUEST);
			}
				
				Authentication auth = authManager.authenticate(new UsernamePasswordAuthenticationToken(
						loginUsuario.getNombreUsuario(),
						loginUsuario.getContrasenia()));
				
				SecurityContextHolder.getContext().setAuthentication(auth);
				
				String jwt = jwtProv.generateToken(auth);
				
				UserDetails userDet = (UserDetails) auth.getPrincipal();
				
				JwtDTO  jwtDto = new JwtDTO(jwt, userDet.getUsername(), userDet.getAuthorities());
				
				return new ResponseEntity(jwtDto, HttpStatus.OK);
			
		}
	}

