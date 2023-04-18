package com.portfolio.ariel.PortfolioAriel.Entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class ExpLaboral {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String nombreExp;
	private String img;
	private String descripcionExp;
	
	//Constructores
	public ExpLaboral() {
		
	}

	public ExpLaboral(String nombreExp, String descripcionExp, String  img) {
		this.nombreExp = nombreExp;
		this.descripcionExp = descripcionExp;
		this.img = img;
	}
	//Gett/SETT

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombreExp() {
		return nombreExp;
	}

	public void setNombreExp(String nombreExp) {
		this.nombreExp = nombreExp;
	}

	public String getDescripcionExp() {
		return descripcionExp;
	}

	public void setDescripcionExp(String descripcionExp) {
		this.descripcionExp = descripcionExp;
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}
	
}
