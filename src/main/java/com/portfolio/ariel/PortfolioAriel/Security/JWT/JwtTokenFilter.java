package com.portfolio.ariel.PortfolioAriel.Security.JWT;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.filter.OncePerRequestFilter;

import com.portfolio.ariel.PortfolioAriel.Security.Service.UserDetailsImpl;

public class JwtTokenFilter extends OncePerRequestFilter {
	private final static Logger logger = LoggerFactory.getLogger(JwtTokenFilter.class);

	@Autowired
	JwtProvider jwtProv;
	@Autowired
	UserDetailsImpl userDetServImp;
	
	private String getToken(HttpServletRequest request) {
		String header = request.getHeader("Authorization");
		if(header != null && header.startsWith("Bearer")) {
			return header.replace("Bearer","");
		}else {
			return null;
		}
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		try {
			String token = getToken(request);
			if (token != null && jwtProv.validateToken(token)) {

				String nombreUsuario = jwtProv.getNombreUsuarioFromToken(token);
				UserDetails userDet = userDetServImp.loadUserByUsername(nombreUsuario);
				UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(userDet,
						null,
						userDet.getAuthorities());
				SecurityContextHolder.getContext().setAuthentication(auth);
			}
		} catch (Exception exc) {
			logger.error("Fallo el metodo doFilterInternal! (JWT)");
		} filterChain.doFilter(request, response);
	}
}
