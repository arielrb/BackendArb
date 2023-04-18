package com.portfolio.ariel.PortfolioAriel.Service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.portfolio.ariel.PortfolioAriel.Entity.Proyectos;
import com.portfolio.ariel.PortfolioAriel.Repository.IProyectos;

@Transactional
@Service
public class SProyectos {
	@Autowired
	IProyectos Rpro;
	
	public List<Proyectos> list(){
		return Rpro.findAll();
	}
	public Optional<Proyectos> GetOne(int id){
		return Rpro.findById(id);
	}
	public Optional<Proyectos> GetByNombre(String nombre){
		return Rpro.findByNombre(nombre);
	}
	public void save(Proyectos proyecto) {
		Rpro.save(proyecto);
	}
	public void delete(int id) {
		Rpro.deleteById(id);
	}
	public boolean ExistById(int id) {
		return Rpro.existsById(id);
	}
	public boolean ExistByNombre(String nombre) {
		return Rpro.existsByNombre(nombre);
	}


}
