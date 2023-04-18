package com.portfolio.ariel.PortfolioAriel.Dto;

import javax.validation.constraints.NotBlank;

public class DtoExpLaboral {
	@NotBlank
	private String nombreExp;
	@NotBlank
	private String descripcionExp;

	private String img;


	//Constructor
	public DtoExpLaboral() {}
	public DtoExpLaboral(@NotBlank String nombreExp, @NotBlank String descripcionExp, String img) {
		this.nombreExp = nombreExp;
		this.descripcionExp = descripcionExp;
		this.img = img;
	}
	//GEtt/Sett
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
