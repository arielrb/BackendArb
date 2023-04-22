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

import com.portfolio.ariel.PortfolioAriel.Dto.DtoEducacion;
import com.portfolio.ariel.PortfolioAriel.Dto.DtoProyectos;
import com.portfolio.ariel.PortfolioAriel.Entity.Educacion;
import com.portfolio.ariel.PortfolioAriel.Entity.Proyectos;
import com.portfolio.ariel.PortfolioAriel.Security.Controller.Mensaje;
import com.portfolio.ariel.PortfolioAriel.Service.SProyectos;

@RestController
@CrossOrigin(origins = {"https://frontendarb.web.app"})
@RequestMapping("/Proyectos")
public class Cproyectos {

	@Autowired
	SProyectos Spro;

	// Traer la lista
	@GetMapping("/lista")
	public ResponseEntity<List<Proyectos>> list() {
		List<Proyectos> list = Spro.list();
		return new ResponseEntity(list, HttpStatus.OK);
	}

	// Crear experiencias
	@PostMapping("/crear")
	public ResponseEntity<?> create(@RequestBody DtoProyectos dtoPro) {
		if (StringUtils.isBlank(dtoPro.getNombre())) {
			return new ResponseEntity(new Mensaje("Nombre obligatorio!"), HttpStatus.BAD_REQUEST);
		}
		if (StringUtils.isBlank(dtoPro.getDescripcion())) {
			return new ResponseEntity(new Mensaje("Descripcion es obligatoria!"), HttpStatus.BAD_REQUEST);
		}
		if (Spro.ExistByNombre(dtoPro.getNombre())) {
			return new ResponseEntity(new Mensaje("Esa ya existia!"), HttpStatus.BAD_REQUEST);
		}

		Proyectos proyecto = new Proyectos(dtoPro.getNombre(), dtoPro.getDescripcion(), dtoPro.getImg());

		Spro.save(proyecto);

		return new ResponseEntity(new Mensaje("Proyecto agregado, Vamo por mas!"), HttpStatus.OK);
	}

	// Actualizar experiencias
	@PutMapping("update/{id}")
	public ResponseEntity<?> update(@PathVariable("id") int id, @RequestBody DtoProyectos dtoPro) {
		// Validar por id
		if (!Spro.ExistById(id)) {
			return new ResponseEntity(new Mensaje("Ese id no existe!"), HttpStatus.BAD_REQUEST);
		}
		// Compara nombres de Habilidades
		if (Spro.ExistByNombre(dtoPro.getNombre()) && Spro.GetByNombre(dtoPro.getNombre()).get().getId() != id) {
			return new ResponseEntity(new Mensaje("La educacion con ese nombre ya existe!"), HttpStatus.BAD_REQUEST);
		}
		if (StringUtils.isBlank(dtoPro.getNombre())) {
			return new ResponseEntity(new Mensaje("El nombre es obligatorio!"), HttpStatus.BAD_REQUEST);
		}
		if (StringUtils.isBlank(dtoPro.getDescripcion())) {
			return new ResponseEntity(new Mensaje("La descripcion es obligatoria!"), HttpStatus.BAD_REQUEST);
		}
		Proyectos proyecto = Spro.GetOne(id).get();
		proyecto.setNombre(dtoPro.getNombre());
		proyecto.setDescripcion(dtoPro.getDescripcion());
		proyecto.setImg(dtoPro.getImg());
		Spro.save(proyecto);
		return new ResponseEntity(new Mensaje("Proyecto Actualizado, Zaplaaaaa!"), HttpStatus.OK);
	}

	// Encontrar por Id
	@GetMapping("/detail/{id}")
	public ResponseEntity<Educacion> getById(@PathVariable("id") int id) {
		if (!Spro.ExistById(id)) {
			return new ResponseEntity(new Mensaje("No existe ese proyecto (por lo menos no todavía)!"), HttpStatus.NOT_FOUND);
		}
		Proyectos proyecto = Spro.GetOne(id).get();
		return new ResponseEntity(proyecto, HttpStatus.OK);
	}

	// Eliminar una experiencia
	@DeleteMapping("/eliminar/{id}")
	public ResponseEntity<?> delete(@PathVariable("id") int id) {
		if (!Spro.ExistById(id)) {
			return new ResponseEntity(new Mensaje("No existe ese proyecto que me decis!"), HttpStatus.NOT_FOUND);
		}

		Spro.delete(id);
		return new ResponseEntity(new Mensaje("Proyecto eliminado!"), HttpStatus.OK);
	}

}
