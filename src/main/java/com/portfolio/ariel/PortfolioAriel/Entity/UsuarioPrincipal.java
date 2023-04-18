package com.portfolio.ariel.PortfolioAriel.Entity;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

//Clase encargada de tener la seguridad implementa UserDetails
@SuppressWarnings("serial")
public class UsuarioPrincipal implements UserDetails{
	
	private String nombre;
	private String nombreUsuario;
	private String email;
	private String contrasenia;
	private Collection<? extends GrantedAuthority> autoridades;
	
	public static UsuarioPrincipal build(Usuario usuario) {
		List<GrantedAuthority> autoridades = usuario.getRoles().stream()
				.map(rol -> new SimpleGrantedAuthority(rol.getRolnombre()
						.name())).collect(Collectors.toList());
		
		return new UsuarioPrincipal(usuario.getNombre(),usuario.getNombreUsuario(),
				usuario.getEmail(),usuario.getContrasenia(),autoridades);
	}
	//Constructores, getters/ setters

	public UsuarioPrincipal(String nombre, String nombreUsuario, String email, String contrasenia,
			Collection<? extends GrantedAuthority> autoridades) {
		this.nombre = nombre;
		this.nombreUsuario = nombreUsuario;
		this.email = email;
		this.contrasenia = contrasenia;
		this.autoridades = autoridades;
	}
	public UsuarioPrincipal() {
		
	}
	//Metodos implementados
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return autoridades;
	}
	@Override
	public String getPassword() {
		return contrasenia;
	}
	@Override
	public String getUsername() {
		return nombreUsuario;
	}	
	
	public String getNombre() {
		return nombre;
	}
	public String getEmail() {
		return email;
	}
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}
	@Override
	public boolean isAccountNonLocked() {
		return true;
	}
	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}
	@Override
	public boolean isEnabled() {
		return true;
	}
	
	
	
	
}
