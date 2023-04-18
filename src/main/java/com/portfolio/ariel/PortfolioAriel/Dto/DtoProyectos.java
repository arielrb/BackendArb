package com.portfolio.ariel.PortfolioAriel.Dto;

import javax.validation.constraints.NotBlank;

public class DtoProyectos {


	@NotBlank
	private String nombre;
	@NotBlank
	private String descripcion;
	
	private String img;

	//Constructores
	public DtoProyectos() {
		
	}
	
	public DtoProyectos(String nombre, String descripcion, String img) {
		super();
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.img = img;
	}

	//Get/Set
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}
	
}
