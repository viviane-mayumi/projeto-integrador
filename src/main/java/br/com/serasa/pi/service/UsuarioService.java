package br.com.serasa.pi.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.serasa.pi.common.UsuarioVO;
import br.com.serasa.pi.domain.entity.UsuarioEntity;
import br.com.serasa.pi.mapper.UsuarioMapper;
import br.com.serasa.pi.repository.UsuarioRepository;

@Service
public class UsuarioService implements UserDetailsService {

	@Autowired
	private UsuarioRepository repository;
	
	@Autowired
	private UsuarioMapper usuarioMapper;

	public UsuarioService(UsuarioRepository repository) {
		this.repository = repository;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		var user = repository.findByUsername(username);

		if (user != null) {
			return user;
		} else {
			throw new UsernameNotFoundException("O usuario " + username + " não localizado");
		}
	}
	
	public UsuarioVO obterUsuarioPorId(Long id) {
		UsuarioVO retorno = null;
		Optional<UsuarioEntity> optionalUsuarioEntity = this.repository.findById(id);
		if(optionalUsuarioEntity.isPresent()) {
			retorno = usuarioMapper.usuarioEntityToUsuarioVO(optionalUsuarioEntity.get()); 
		}
		return retorno;
	}

	public List<UsuarioVO> obterTodosUsuarios() {
		List<UsuarioEntity> usuariosEntity = this.repository.findAll();
		return usuarioMapper.listUsuarioEntityToListUsuarioVO(usuariosEntity);
	}
}
