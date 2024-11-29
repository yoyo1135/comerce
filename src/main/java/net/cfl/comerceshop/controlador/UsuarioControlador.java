package net.cfl.comerceshop.controlador;

pimport org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import net.cfl.comerceshop.dto.UsuarioDto;
import net.cfl.comerceshop.excepciones.RecursoNoEncontradoEx;
import net.cfl.comerceshop.excepciones.UsuarioExisteEx;
import net.cfl.comerceshop.modelo.Usuario;
import net.cfl.comerceshop.request.ActualizaUsuarioReq;
import net.cfl.comerceshop.request.AgregaUsuarioReq;
import net.cfl.comerceshop.respuesta.ApiRespuesta;
import net.cfl.comerceshop.servicios.carrito.ICarritoServicio;
import net.cfl.comerceshop.servicios.producto.IProductoServicio;
import net.cfl.comerceshop.servicios.usuario.IUsuarioServicio;

@RequiredArgsConstructor
@RestController
@RequestMapping("${api.prefix}/usuarios")
public class UsuarioControlador {
	private final IUsuarioServicio usuarioServicio;

	@GetMapping("/{usuarioId}/usuario")
	public ResponseEntity<ApiRespuesta> traeUsuario(@PathVariable Long usuarioId){
		try {
			Usuario usuario = usuarioServicio.traeUsuarioPorId(usuarioId);
			UsuarioDto usuarioDto = usuarioServicio.convertirAUsuarioDto(usuario);
			return ResponseEntity.ok(new ApiRespuesta("Exito!", usuarioDto));
		} catch (RecursoNoEncontradoEx e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body(new ApiRespuesta(e.getMessage(), null));
		}
	}
	
	@PostMapping("/agrega")
	public ResponseEntity<ApiRespuesta> creaUsuario (@RequestBody AgregaUsuarioReq request){
		try {
			Usuario usuario = usuarioServicio.crearUsuario(request);
			UsuarioDto usuarioDto = usuarioServicio.convertirAUsuarioDto(usuario);
			return ResponseEntity.ok(new ApiRespuesta("Usuario creado!", usuarioDto));
		} catch (UsuarioExisteEx e) {
			return ResponseEntity.status(HttpStatus.CONFLICT)
					.body(new ApiRespuesta(e.getMessage(), null));
		}
	}
	
	@PutMapping("/{usuarioId}/actualiza")
	public ResponseEntity<ApiRespuesta> actualizaUsuario (@RequestBody ActualizaUsuarioReq request, @PathVariable Long usuarioId){
		try {
			Usuario usuario= usuarioServicio.actualizarUsuario(request, usuarioId);
			UsuarioDto usuarioDto = usuarioServicio.convertirAUsuarioDto(usuario);
			return ResponseEntity.ok(new ApiRespuesta("Usuario actualizado!", usuarioDto));
		} catch (RecursoNoEncontradoEx e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body(new ApiRespuesta(e.getMessage(), null));
		}
	}
	
	@DeleteMapping("/{usuarioId}/borrar")
	public ResponseEntity<ApiRespuesta> eliminaUsuario(@PathVariable Long usuarioId){
		try {
			usuarioServicio.borrarUsuario(usuarioId);
			return ResponseEntity.ok(new ApiRespuesta("Usuario eliminado!", null));
		} catch (RecursoNoEncontradoEx e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body(new ApiRespuesta(e.getMessage(), null));
		}
	}
}