package com.portfolio.ariel.PortfolioAriel.Security.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.portfolio.ariel.PortfolioAriel.Entity.Usuario;
import com.portfolio.ariel.PortfolioAriel.Entity.UsuarioPrincipal;

@Service
public class UserDetailsImpl implements UserDetailsService{
	@Autowired
	UsuarioService usuarioServ;
	
	
	@Override
	public UserDetails loadUserByUsername(String nombreUsuario) throws UsernameNotFoundException {
		Usuario user = usuarioServ.getByNombreUsuario(nombreUsuario).get();
		return UsuarioPrincipal.build(user);
	}

}
