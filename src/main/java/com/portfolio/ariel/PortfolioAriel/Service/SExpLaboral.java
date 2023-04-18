package com.portfolio.ariel.PortfolioAriel.Service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

import com.portfolio.ariel.PortfolioAriel.Entity.ExpLaboral;
import com.portfolio.ariel.PortfolioAriel.Repository.IExpLaboralRepository;

@Service
@Transactional
public class SExpLaboral {
	@Autowired
	IExpLaboralRepository iExp;
	
	public List<ExpLaboral> list(){
		return iExp.findAll();
	}
	
	public Optional<ExpLaboral> getOne(int id){
		return iExp.findById(id);
	}
	
	public Optional<ExpLaboral> getByNombreExp(String nombreExp){
		return iExp.findByNombreExp(nombreExp);
	}
	public void save(ExpLaboral exp) {
		iExp.save(exp);
	}
	public void delete(int id) {
		iExp.deleteById(id);
	}
	public boolean existById(int id) {
		return iExp.existsById(id);
	}
	public boolean existByNombreExp(String nombreExp) {
		return iExp.existsByNombreExp(nombreExp);
	}

}
