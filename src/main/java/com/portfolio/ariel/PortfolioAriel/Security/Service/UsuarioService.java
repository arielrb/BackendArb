package com.portfolio.ariel.PortfolioAriel.Security.Service;

import javax.transaction.Transactional;
import com.portfolio.ariel.PortfolioAriel.Entity.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;
import com.portfolio.ariel.PortfolioAriel.Security.Repository.IUsuarioRepository;

@Service
@Transactional
public class UsuarioService {
	@Autowired
	IUsuarioRepository iUsuarioRepo;
	
	public Optional<Usuario> getByNombreUsuario(String nombreUsuario){
		return iUsuarioRepo.findByNombreUsuario(nombreUsuario);
	}
	
	public boolean existByNombreUsuario(String nombreUsuario) {
		return iUsuarioRepo.existsByNombreUsuario(nombreUsuario);
	}
	
	public boolean existByEmail(String email) {
		return iUsuarioRepo.existsByemail(email);
	}
	
	public void save(Usuario usuario) {
		iUsuarioRepo.save(usuario);
	}
}
