package com.portfolio.ariel.PortfolioAriel.Controller;

import java.util.List;

import org.apache.commons.lang3.StringUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.portfolio.ariel.PortfolioAriel.Dto.DtoHabilidades;
import com.portfolio.ariel.PortfolioAriel.Entity.ExpLaboral;
import com.portfolio.ariel.PortfolioAriel.Entity.Habilidades;
import com.portfolio.ariel.PortfolioAriel.Security.Controller.Mensaje;
import com.portfolio.ariel.PortfolioAriel.Service.SHabilidades;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/Habilidades")
public class Chabilidades {
	@Autowired
	SHabilidades Shab;

	// Traer la lista
	@GetMapping("/lista")
	public ResponseEntity<List<Habilidades>> list() {
		List<Habilidades> list = Shab.list();
		return new ResponseEntity(list, HttpStatus.OK);
	}

	// Crear experiencias
	@PostMapping("/crear")
	public ResponseEntity<?> create(@RequestBody DtoHabilidades dtoHab) {
		if (StringUtils.isBlank(dtoHab.getNombre())) {
			return new ResponseEntity(new Mensaje("Nombre obligatorio!"), HttpStatus.BAD_REQUEST);
		}
		if (Shab.ExistByNombre(dtoHab.getNombre())) {
			return new ResponseEntity(new Mensaje("Esa Habilidad ya existia!"), HttpStatus.BAD_REQUEST);
		}

		Habilidades habilidad= new Habilidades( dtoHab.getNombre(), dtoHab.getPorcentaje(), dtoHab.getImg());

		Shab.save(habilidad);

		return new ResponseEntity(new Mensaje("Habilidad agregada!"), HttpStatus.OK);
	}

	// Actualizar experiencias
	@PutMapping("update/{id}")
	public ResponseEntity<?> update(@PathVariable("id") int id, @RequestBody DtoHabilidades dtoHab) {
		// Validar por id
		if (!Shab.ExistById(id)) {
			return new ResponseEntity(new Mensaje("Ese id no existe!"), HttpStatus.BAD_REQUEST);
		}
		// Compara nombres de Habilidades
		if (Shab.ExistByNombre(dtoHab.getNombre())
				&& Shab.GetByNombre(dtoHab.getNombre()).get().getId() != id) {
			return new ResponseEntity(new Mensaje("La habilidad con ese nombre ya existe!"), HttpStatus.BAD_REQUEST);
		}
		if (StringUtils.isBlank(dtoHab.getNombre())) {
			return new ResponseEntity(new Mensaje("El nombre es obligatorio!"), HttpStatus.BAD_REQUEST);
		}
		/* A hacer, tengo que ver la forma de verificar si el campo esta vacio
		if ( dtoHab.getPorcentaje().isEmpty()) {
			return new ResponseEntity(new Mensaje("El Porcentaje es obligatorio!"), HttpStatus.BAD_REQUEST);
		} */
		Habilidades habilidad= Shab.GetOne(id).get();
		habilidad.setNombre(dtoHab.getNombre());
		habilidad.setPorcentaje(dtoHab.getPorcentaje());
		Shab.save(habilidad);
		return new ResponseEntity(new Mensaje("Habilidad Actualizada, master!"), HttpStatus.OK);
	}

	// Encontrar por Id
	@GetMapping("/detail/{id}")
	public ResponseEntity<Habilidades> getById(@PathVariable("id") int id) {
		if (!Shab.ExistById(id)) {
			return new ResponseEntity(new Mensaje("No existe esa Habilidad!"), HttpStatus.NOT_FOUND);
		}
		Habilidades habilidad = Shab.GetOne(id).get();
		return new ResponseEntity(habilidad, HttpStatus.OK);
	}

	// Eliminar una experiencias
	@DeleteMapping("/eliminar/{id}")
	public ResponseEntity<?> delete(@PathVariable("id") int id) {
		if (!Shab.ExistById(id)) {
			return new ResponseEntity(new Mensaje("No existe esa Habilidad!"), HttpStatus.NOT_FOUND);
		}

		Shab.delete(id);
		return new ResponseEntity(new Mensaje("Habilidad eliminada!"), HttpStatus.OK);
	}

}
