package br.com.serasa.pi.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import br.com.serasa.pi.common.UsuarioVO;
import br.com.serasa.pi.domain.entity.UsuarioEntity;

@Mapper(componentModel = "spring")
public interface UsuarioMapper {
	UsuarioMapper INSTANCE = Mappers.getMapper(UsuarioMapper.class);

	UsuarioVO usuarioEntityToUsuarioVO(UsuarioEntity Usuario);
	
	UsuarioEntity UsuarioVOToUsuarioEntity(UsuarioVO UsuarioVO);
	
	List<UsuarioVO> listUsuarioEntityToListUsuarioVO(List<UsuarioEntity> Usuario);
	
	List<UsuarioEntity> listUsuarioVOToListUsuarioEntity(List<UsuarioVO> UsuarioVO);

	default UsuarioEntity createUsuario() {
		return new UsuarioEntity();
	}
}
