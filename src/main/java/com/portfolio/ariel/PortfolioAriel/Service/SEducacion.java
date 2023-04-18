package com.portfolio.ariel.PortfolioAriel.Service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.portfolio.ariel.PortfolioAriel.Entity.Educacion;
import com.portfolio.ariel.PortfolioAriel.Repository.IEducacion;

@Transactional
@Service
public class SEducacion {
	@Autowired
	IEducacion Redu;
	
	public List<Educacion> list(){
		return Redu.findAll();
	}
	public Optional<Educacion> GetOne(int id){
		return Redu.findById(id);
	}
	public Optional<Educacion> GetByNombre(String nombre){
		return Redu.findByNombre(nombre);
	}
	public void save(Educacion educacion) {
		Redu.save(educacion);
	}
	public void delete(int id) {
		Redu.deleteById(id);
	}
	public boolean ExistById(int id) {
		return Redu.existsById(id);
	}
	public boolean ExistByNombre(String nombre) {
		return Redu.existsByNombre(nombre);
	}

}
