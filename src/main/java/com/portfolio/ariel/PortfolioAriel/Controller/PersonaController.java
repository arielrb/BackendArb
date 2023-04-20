package com.portfolio.ariel.PortfolioAriel.Controller;

import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.portfolio.ariel.PortfolioAriel.Dto.DtoPersona;
import com.portfolio.ariel.PortfolioAriel.Entity.Persona;
import com.portfolio.ariel.PortfolioAriel.Security.Controller.Mensaje;
import com.portfolio.ariel.PortfolioAriel.Service.SPersona;

@RestController
@CrossOrigin(origins = {"https://frontendarb.web.app","http://localhost:4200"})
@RequestMapping("/MiPersona")
public class PersonaController {

	@Autowired
	SPersona Sper;

	// Traer la lista
	@GetMapping("/lista")
	public ResponseEntity<List<Persona>> list() {
		List<Persona> list = Sper.list();
		return new ResponseEntity(list, HttpStatus.OK);
	}

	
	// Actualizar experiencias
	@PutMapping("update/{id}")
	public ResponseEntity<?> update(@PathVariable("id") long id, @RequestBody DtoPersona dtoPer) {
		// Validar por id
		if (!Sper.ExistById(id)) {
			return new ResponseEntity(new Mensaje("Ese id no existe!"), HttpStatus.BAD_REQUEST);
		}
		if (StringUtils.isBlank(dtoPer.getNombre())) {
			return new ResponseEntity(new Mensaje("El nombre es obligatorio!"), HttpStatus.BAD_REQUEST);
		}
		if (StringUtils.isBlank(dtoPer.getDescripcion())) {
			return new ResponseEntity(new Mensaje("La descripcion es obligatoria!"), HttpStatus.BAD_REQUEST);
		}
		if (StringUtils.isBlank(dtoPer.getApellido())) {
			return new ResponseEntity(new Mensaje("El apellido es obligatorio!"), HttpStatus.BAD_REQUEST);
		}
		Persona perso = Sper.GetOne(id).get();
		perso.setNombre(dtoPer.getNombre());
		perso.setDescripcion(dtoPer.getDescripcion());
		perso.setApellido(dtoPer.getApellido());
		perso.setImg(dtoPer.getImg());
		Sper.save(perso);
		return new ResponseEntity(new Mensaje("Ahi va, informacion actualizada!"), HttpStatus.OK);
	}

	// Encontrar por Id
	@GetMapping("/detail/{id}")
	public ResponseEntity<Persona> getById(@PathVariable("id") long id) {
		if (!Sper.ExistById(id)) {
			return new ResponseEntity(new Mensaje("No existe esa Persona!"), HttpStatus.NOT_FOUND);
		}
		Persona perso= Sper.GetOne(id).get();
		return new ResponseEntity(perso, HttpStatus.OK);
	}

}
