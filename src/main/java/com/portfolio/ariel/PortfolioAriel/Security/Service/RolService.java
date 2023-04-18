package com.portfolio.ariel.PortfolioAriel.Security.Service;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.portfolio.ariel.PortfolioAriel.Security.Entity.Rol;
import com.portfolio.ariel.PortfolioAriel.Security.Enums.RolNombre;
import com.portfolio.ariel.PortfolioAriel.Security.Repository.IRolRepository;

@Service
@Transactional
public class RolService {
	@Autowired
	IRolRepository iRolRepo;
	
	public Optional<Rol> getByRolNombre(RolNombre rolnombre){
		return iRolRepo.findByRolNombre(rolnombre);
	}
	
	public void save(Rol rol) {
		iRolRepo.save(rol);
	}
}
