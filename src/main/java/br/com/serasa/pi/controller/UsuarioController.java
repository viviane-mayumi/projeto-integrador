package br.com.serasa.pi.controller;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.serasa.pi.common.UsuarioVO;
import br.com.serasa.pi.service.UsuarioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Coleta Endpoint")
@RestController
@RequestMapping("api/usuario")
public class UsuarioController {

	@Autowired
	private UsuarioService usuarioService;

	@CrossOrigin("localhost:8080")
	@Operation(summary = "Listar todas os Usuarios")
	@GetMapping(produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<List<UsuarioVO>> findAll() {
		List<UsuarioVO> usuariosVO = usuarioService.obterTodosUsuarios();
		usuariosVO.stream()
				.forEach(p -> p.add(linkTo(methodOn(UsuarioController.class).findById(p.getId())).withSelfRel()));
		return ResponseEntity.ok().body(usuariosVO);
	}

	@CrossOrigin({ "localhost:8080", "http://www.preservacaoquelonios.com.br" })
	@Operation(summary = "Listar o Usuario por id")
	@GetMapping(value = "/{id}", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<UsuarioVO> findById(@PathVariable("id") Long idUsuario) {
		UsuarioVO usuarioVO = usuarioService.obterUsuarioPorId(idUsuario);
		usuarioVO.add(linkTo(methodOn(UsuarioController.class).findById(idUsuario)).withSelfRel());
		return ResponseEntity.ok().body(usuarioVO);
	}
}
