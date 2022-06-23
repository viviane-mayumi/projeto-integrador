package br.com.serasa.pi.common;

import org.springframework.hateoas.RepresentationModel;

import br.com.serasa.pi.enums.TipoUsuarioEnum;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsuarioVO extends RepresentationModel<UsuarioVO> {
	
	private Long id;

	private String username;

	private String fullName;

	private String password;

	private Boolean accountNonExpired;

	private Boolean accountNonLocked;

	private Boolean credentialsNonExpired;

	private Boolean enabled;
	
	private TipoUsuarioEnum tipoUsuario;
}
