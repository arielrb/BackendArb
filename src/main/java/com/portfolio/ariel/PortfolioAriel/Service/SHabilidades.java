package com.portfolio.ariel.PortfolioAriel.Service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.portfolio.ariel.PortfolioAriel.Entity.Habilidades;
import com.portfolio.ariel.PortfolioAriel.Repository.IHabilidadesRepository;

@Transactional
@Service
public class SHabilidades {
	@Autowired
	IHabilidadesRepository Rhab;
	
	public List<Habilidades> list(){
		return Rhab.findAll();
	}
	public Optional<Habilidades> GetOne(int id){
		return Rhab.findById(id);
	}
	public Optional<Habilidades> GetByNombre(String nombre){
		return Rhab.findByNombre(nombre);
	}
	public void save(Habilidades habilidad) {
		Rhab.save(habilidad);
	}
	public void delete(int id) {
		Rhab.deleteById(id);
	}
	public boolean ExistById(int id) {
		return Rhab.existsById(id);
	}
	public boolean ExistByNombre(String nombre) {
		return Rhab.existsByNombre(nombre);
	}

}
