package com.portfolio.ariel.PortfolioAriel.Security.JWT;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

//Chequeo de si el token enviado es valido
@Component
public class JwtEntryPoint implements AuthenticationEntryPoint{

	private final static Logger logger = LoggerFactory.getLogger(JwtEntryPoint.class);
	
	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException authException) throws IOException, ServletException {
		logger.error("Fallo el metodo Commence!");
		response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
	}
	

}
