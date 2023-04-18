package com.portfolio.ariel.PortfolioAriel.Security.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.portfolio.ariel.PortfolioAriel.Entity.Usuario;

@Repository
public interface IUsuarioRepository extends JpaRepository<Usuario, Integer>{
	Optional<Usuario> findByNombreUsuario(String nombreUsuario);
	
	//Devuelve true or false si existe o no
	boolean existsByNombreUsuario(String nombreUsuario);
	boolean existsByemail(String email);
}
