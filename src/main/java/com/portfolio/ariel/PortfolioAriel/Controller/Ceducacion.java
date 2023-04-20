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
import com.portfolio.ariel.PortfolioAriel.Entity.Educacion;
import com.portfolio.ariel.PortfolioAriel.Security.Controller.Mensaje;
import com.portfolio.ariel.PortfolioAriel.Service.SEducacion;

@RestController
@CrossOrigin(origins = {"https://frontendarb.web.app","http://localhost:4200"})
@RequestMapping("/Educacion")
public class Ceducacion {

	@Autowired
	SEducacion Sedu;

	// Traer la lista
	@GetMapping("/lista")
	public ResponseEntity<List<Educacion>> list() {
		List<Educacion> list = Sedu.list();
		return new ResponseEntity(list, HttpStatus.OK);
	}

	// Crear experiencias
	@PostMapping("/crear")
	public ResponseEntity<?> create(@RequestBody DtoEducacion dtoEdu) {
		if (StringUtils.isBlank(dtoEdu.getNombre())) {
			return new ResponseEntity(new Mensaje("Nombre obligatorio!"), HttpStatus.BAD_REQUEST);
		}
		if (Sedu.ExistByNombre(dtoEdu.getNombre())) {
			return new ResponseEntity(new Mensaje("Esa ya existia!"), HttpStatus.BAD_REQUEST);
		}
		if (StringUtils.isBlank(dtoEdu.getDescripcion())) {
			return new ResponseEntity(new Mensaje("Descripcion es obligatoria!"), HttpStatus.BAD_REQUEST);
		}

		Educacion educacion = new Educacion(dtoEdu.getNombre(), dtoEdu.getDescripcion(), dtoEdu.getImg());

		Sedu.save(educacion);

		return new ResponseEntity(new Mensaje("Educacion agregada, que nivel!"), HttpStatus.OK);
	}

	// Actualizar experiencias
	@PutMapping("update/{id}")
	public ResponseEntity<?> update(@PathVariable("id") int id, @RequestBody DtoEducacion dtoEdu) {
		// Validar por id
		if (!Sedu.ExistById(id)) {
			return new ResponseEntity(new Mensaje("Ese id no existe!"), HttpStatus.BAD_REQUEST);
		}
		// Compara nombres de Habilidades
		if (Sedu.ExistByNombre(dtoEdu.getNombre()) && Sedu.GetByNombre(dtoEdu.getNombre()).get().getId() != id) {
			return new ResponseEntity(new Mensaje("La educacion con ese nombre ya existe!"), HttpStatus.BAD_REQUEST);
		}
		if (StringUtils.isBlank(dtoEdu.getNombre())) {
			return new ResponseEntity(new Mensaje("El nombre es obligatorio!"), HttpStatus.BAD_REQUEST);
		}
		if (StringUtils.isBlank(dtoEdu.getDescripcion())) {
			return new ResponseEntity(new Mensaje("Descripcion es obligatoria!"), HttpStatus.BAD_REQUEST);
		}
		Educacion educacion = Sedu.GetOne(id).get();
		educacion.setNombre(dtoEdu.getNombre());
		educacion.setDescripcion(dtoEdu.getDescripcion());
		educacion.setImg(dtoEdu.getImg());
		Sedu.save(educacion);
		return new ResponseEntity(new Mensaje("Educacion Actualizada, crack!"), HttpStatus.OK);
	}

	// Encontrar por Id
	@GetMapping("/detail/{id}")
	public ResponseEntity<Educacion> getById(@PathVariable("id") int id) {
		if (!Sedu.ExistById(id)) {
			return new ResponseEntity(new Mensaje("No existe esa Educacion!"), HttpStatus.NOT_FOUND);
		}
		Educacion educacion = Sedu.GetOne(id).get();
		return new ResponseEntity(educacion, HttpStatus.OK);
	}

	// Eliminar una experiencia
	@DeleteMapping("/eliminar/{id}")
	public ResponseEntity<?> delete(@PathVariable("id") int id) {
		if (!Sedu.ExistById(id)) {
			return new ResponseEntity(new Mensaje("No existe esa educacion que me decis!"), HttpStatus.NOT_FOUND);
		}

		Sedu.delete(id);
		return new ResponseEntity(new Mensaje("Educacion eliminada!"), HttpStatus.OK);
	}

}
