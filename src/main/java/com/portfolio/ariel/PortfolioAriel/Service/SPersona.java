package com.portfolio.ariel.PortfolioAriel.Service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.portfolio.ariel.PortfolioAriel.Entity.Persona;
import com.portfolio.ariel.PortfolioAriel.Repository.IPersonaRepository;
@Transactional
@Service
public class SPersona{
	//Traemos la dependencia repositorio que lee los campos y tiene los metodos, lo hacemos con @autowire
	@Autowired 
	IPersonaRepository Rper;
		
	public List<Persona> list(){
		return Rper.findAll();
	}
	public Optional<Persona> GetOne(long id){
		return Rper.findById(id);
	}
	public Optional<Persona> GetByNombre(String nombre){
		return Rper.findByNombre(nombre);
	}
	public void save(Persona perso) {
		Rper.save(perso);
	}
	public void delete(long id) {
		Rper.deleteById(id);
	}
	public boolean ExistById(long id) {
		return Rper.existsById(id);
	}
	public boolean ExistByNombre(String nombre) {
		return Rper.existsByNombre(nombre);
	}

}
