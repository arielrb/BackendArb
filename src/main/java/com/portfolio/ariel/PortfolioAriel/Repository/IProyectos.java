package com.portfolio.ariel.PortfolioAriel.Repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.portfolio.ariel.PortfolioAriel.Entity.Proyectos;

@Repository
public interface IProyectos extends JpaRepository<Proyectos, Integer>{
	
	public Optional<Proyectos> findByNombre(String nombre);
	public boolean existsByNombre(String nombre);

}
