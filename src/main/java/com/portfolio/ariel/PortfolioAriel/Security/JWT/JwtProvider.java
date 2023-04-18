package com.portfolio.ariel.PortfolioAriel.Security.JWT;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import com.portfolio.ariel.PortfolioAriel.Entity.UsuarioPrincipal;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

//Esta clase genera el token y tiene metodos de validacion
@Component
public class JwtProvider {
	
	private final static Logger logger = LoggerFactory.getLogger(JwtProvider.class);
	
	@Value("${jwt.secret}")
    private String secret;
    @Value("${jwt.expiration}")
    private int expiration;

	
	
	public String generateToken(Authentication autenticacion) {
		//Es usuario prinicipal porque es el que se logea
		UsuarioPrincipal usuarioPrin = (UsuarioPrincipal) autenticacion.getPrincipal();
		return Jwts.builder().setSubject(usuarioPrin.getUsername())
				.setIssuedAt(new Date())
				.setExpiration(new Date(new Date().getTime()+expiration*1000))
				.signWith(SignatureAlgorithm.HS512,secret).compact();
	}
	public String getNombreUsuarioFromToken(String token) {
		return Jwts.parser()
				.setSigningKey(secret)
				.parseClaimsJws(token)
				.getBody()
				.getSubject();
	}
	
	public boolean validateToken(String token) {
		try {
			Jwts.parser()
			.setSigningKey(secret)
			.parse(token);
			return true;
		}
		catch (MalformedJwtException error){
			logger.error("Token Mal formado!");
		}
		catch (UnsupportedJwtException error){
			logger.error("Token no soportado!");
		}
		catch (ExpiredJwtException error){
			logger.error("Token expirado!");
		}
		catch (IllegalArgumentException error){
			logger.error("Token Vacio o ilegal!");
		}
		catch (SignatureException error){
			logger.error("Firma invalida!");
		}
		return false;
	}
	
	
}
