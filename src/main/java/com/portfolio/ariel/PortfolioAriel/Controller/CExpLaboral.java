package com.portfolio.ariel.PortfolioAriel.Controller;

import java.net.http.HttpResponse;
import java.util.List;
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
import com.portfolio.ariel.PortfolioAriel.Dto.DtoExpLaboral;
import com.portfolio.ariel.PortfolioAriel.Entity.ExpLaboral;
import com.portfolio.ariel.PortfolioAriel.Security.Controller.Mensaje;
import com.portfolio.ariel.PortfolioAriel.Service.SExpLaboral;
import org.apache.commons.lang3.StringUtils;

@RestController
@RequestMapping("/ExperienciaLaboral")
@CrossOrigin(origins = {"https://frontendarb.web.app"})
public class CExpLaboral {
	@Autowired
	SExpLaboral sExp;
	
	//Traer la lista
	@GetMapping("/lista")
	public ResponseEntity<List<ExpLaboral>> list(){
		List<ExpLaboral> list = sExp.list();
		return new ResponseEntity(list,HttpStatus.OK);
	}
	//Crear experiencias
	@PostMapping("/crear")
	public ResponseEntity<?> create(@RequestBody DtoExpLaboral dtoExp){
		if(StringUtils.isBlank(dtoExp.getNombreExp())) {
			return new ResponseEntity(new Mensaje("Nombre obligatorio!"),HttpStatus.BAD_REQUEST);
		}
		if(sExp.existByNombreExp(dtoExp.getNombreExp())) {
			return new ResponseEntity(new Mensaje("Esa experiencia ya existia!"),HttpStatus.BAD_REQUEST);
		}
		if (StringUtils.isBlank(dtoExp.getDescripcionExp())) {
			return new ResponseEntity(new Mensaje("Descripcion es obligatoria!"), HttpStatus.BAD_REQUEST);
		}
		
		ExpLaboral expLab = new ExpLaboral(dtoExp.getNombreExp(),dtoExp.getDescripcionExp(), dtoExp.getImg());
		
		sExp.save(expLab);
		
		return new ResponseEntity(new Mensaje("Experiencia agregada!"),HttpStatus.OK);
	}

	//Actualizar experiencias
	@PutMapping("update/{id}")
	public ResponseEntity<?> update(@PathVariable("id") int id, @RequestBody DtoExpLaboral dtoExp){
		//Validar por id
		if(!sExp.existById(id)) {
			return new ResponseEntity(new Mensaje("Ese id no existe!"),HttpStatus.BAD_REQUEST);
		}
		//Compara nombres de experiencias
		if (sExp.existByNombreExp(dtoExp.getNombreExp()) && sExp.getByNombreExp(dtoExp.getNombreExp()).get().getId() != id ) {
			return new ResponseEntity(new Mensaje("La experiencia con ese nombre ya existe!"),HttpStatus.BAD_REQUEST); 
		}
		if (StringUtils.isBlank(dtoExp.getNombreExp())) {
			return new ResponseEntity(new Mensaje("El nombre es obligatorio!"),HttpStatus.BAD_REQUEST);
		}
		if (StringUtils.isBlank(dtoExp.getDescripcionExp())) {
			return new ResponseEntity(new Mensaje("Descripcion es obligatoria!"), HttpStatus.BAD_REQUEST);
		}
		ExpLaboral expLab = sExp.getOne(id).get();
		expLab.setNombreExp(dtoExp.getNombreExp());
		expLab.setDescripcionExp(dtoExp.getDescripcionExp());
		expLab.setImg(dtoExp.getImg());
		sExp.save(expLab);
		return new ResponseEntity(new Mensaje("Experiencia Actualizada!"),HttpStatus.OK);
	}

	//Encontrar por Id
	@GetMapping("/detail/{id}")
    public ResponseEntity<ExpLaboral> getById(@PathVariable("id") int id){
        if(!sExp.existById(id)) {
            return new ResponseEntity(new Mensaje("No existe esa Experiencia!"), HttpStatus.NOT_FOUND);
        }
        ExpLaboral expLab= sExp.getOne(id).get();
        return new ResponseEntity(expLab, HttpStatus.OK);
    }
	
	//Eliminar una experiencias
	@DeleteMapping("/eliminar/{id}")
	public ResponseEntity<?> delete(@PathVariable("id")int id){
		if(!sExp.existById(id)) {
            return new ResponseEntity(new Mensaje("No existe esa Experiencia!"), HttpStatus.NOT_FOUND);
        }
		
		sExp.delete(id);
		return new ResponseEntity(new Mensaje("Experiencia eliminada!"), HttpStatus.OK);
	}
}
