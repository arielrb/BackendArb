package com.portfolio.ariel.PortfolioAriel.Entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Habilidades {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String nombre;
	private String  img;
	private int porcentaje;
	//Constructor

	public Habilidades() {
		
	}
	public Habilidades(String nombre, int porcentaje, String img) {
		this.nombre = nombre;
		this.porcentaje = porcentaje;
		this.img = img;
	}
	
	//Gett/Sett

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public int getPorcentaje() {
		return porcentaje;
	}
	public void setPorcentaje(int porcentaje) {
		this.porcentaje = porcentaje;
	}
	public String getImg() {
		return img;
	}
	public void setImg(String img) {
		this.img = img;
	}

	
	
	
	
}
