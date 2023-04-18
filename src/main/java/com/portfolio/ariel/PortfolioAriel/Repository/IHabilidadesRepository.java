package com.portfolio.ariel.PortfolioAriel.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.portfolio.ariel.PortfolioAriel.Entity.Habilidades;

@Repository
public interface IHabilidadesRepository extends JpaRepository<Habilidades, Integer> {

	public Optional<Habilidades> findByNombre(String nombre);
	public boolean existsByNombre(String nombre);

}
