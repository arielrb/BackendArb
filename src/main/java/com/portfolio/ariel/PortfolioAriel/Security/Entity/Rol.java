package com.portfolio.ariel.PortfolioAriel.Security.Entity;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

import com.portfolio.ariel.PortfolioAriel.Security.Enums.RolNombre;

@Entity
public class Rol {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@NotNull
	@Enumerated(EnumType.STRING)
	private RolNombre rolNombre;
	
	//constructores
	public Rol() {
		
	}
	public Rol(int id, @NotNull RolNombre rolNombre) {
		this.rolNombre = rolNombre;
	}
	//Getters/Setters
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public RolNombre getRolnombre() {
		return rolNombre;
	}
	public void setRolnombre(RolNombre rolNombre) {
		this.rolNombre = rolNombre;
	}
	
	

}
