package br.com.serasa.pi.domain.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import br.com.serasa.pi.enums.TipoUsuarioEnum;
import lombok.Data;

@Data
@Entity
@Table(name = "usuario")
public class UsuarioEntity implements UserDetails, Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@Column(name = "full_name")
	private String fullName;

	@Enumerated(EnumType.STRING)
	@Column(name = "tipoUsuario", columnDefinition = "enum('ADMIN','COORDENADOR','VOLUNTARIO')")
	private TipoUsuarioEnum tipoUsuario;

	@ManyToMany(cascade  = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinTable(name = "usuario_permissao", joinColumns = { @JoinColumn(name = "id_usuario", nullable = false) }, inverseJoinColumns = {
			@JoinColumn(name = "id_permissao", nullable = false) })
	private List<PermissaoEntity> permissions;
	
	@Column(name = "user_name", unique = true)
	private String username;
	
	@Column(name = "password")
	private String password;

	@Column(name = "account_non_expired")
	private Boolean accountNonExpired;

	@Column(name = "account_non_locked")
	private Boolean accountNonLocked;

	@Column(name = "credentials_non_expired")
	private Boolean credentialsNonExpired;

	@Column(name = "enabled")
	private Boolean enabled;
		
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return this.permissions;
	}

	@Override
	public String getPassword() {
		return this.password;
	}

	@Override
	public String getUsername() {
		return this.username;
	}

	@Override
	public boolean isAccountNonExpired() {
		return this.accountNonExpired;
	}

	@Override
	public boolean isAccountNonLocked() {
		return this.accountNonLocked;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return this.credentialsNonExpired;

	}

	@Override
	public boolean isEnabled() {
		return this.enabled;
	}

	public List<String> getRoles() {
		List<String> roles = new ArrayList<>();
		for (PermissaoEntity permission : this.permissions) {
			roles.add(permission.getDescription());
		}
		return roles;
	}
}
