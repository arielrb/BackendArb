package com.portfolio.ariel.PortfolioAriel.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.portfolio.ariel.PortfolioAriel.Entity.ExpLaboral;

@Repository
public interface IExpLaboralRepository extends JpaRepository<ExpLaboral, Integer>{
	
	public Optional<ExpLaboral> findByNombreExp(String nombreExp);
	public boolean existsByNombreExp(String nombreExp);
	
}
