package net.cfl.comerceshop.controlador;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import net.cfl.comerceshop.excepciones.RecursoNoEncontradoEx;
import net.cfl.comerceshop.modelo.Carrito;
import net.cfl.comerceshop.modelo.Usuario;
import net.cfl.comerceshop.respuesta.ApiRespuesta;
import net.cfl.comerceshop.servicios.carrito.ICarritoItemServicio;
import net.cfl.comerceshop.servicios.carrito.ICarritoServicio;
import net.cfl.comerceshop.servicios.usuario.IUsuarioServicio;

@RequiredArgsConstructor
@RestController
@RequestMapping("${api.prefix}/items-carrito")
public class CarritoItemControlador {
	private final ICarritoItemServicio carritoItemServicio;
	private final ICarritoServicio carritoServicio;
	private final IUsuarioServicio usuarioServicio;
	
	@PostMapping("/item/agrega")
	public ResponseEntity<ApiRespuesta> agregaItemAlCarrito(
															@RequestParam Long productoId, 
															@RequestParam Integer cantidad){
		try {
			Usuario usuario = usuarioServicio.traeUsuarioPorId(1L);	
			Carrito carrito = carritoServicio.inicializaCarrito(usuario);
	
			carritoItemServicio.agregaItemAlCarrito(carrito.getId(), productoId, cantidad);
			return ResponseEntity.ok(new ApiRespuesta("item agregado con exito!", null));
		} catch (RecursoNoEncontradoEx e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiRespuesta(e.getMessage(), null));
		}
	}
	@DeleteMapping("/carrito/{carritoId}/item/{productoId}/quitar")
	public ResponseEntity<ApiRespuesta> quitaItemDelCarrito(@PathVariable Long carritoId, 
															@PathVariable Long productoId){
		try {
			carritoItemServicio.quitaItemDelCarrito(carritoId, productoId);
			return ResponseEntity.ok(new ApiRespuesta("item quitado con exito!", null));
		} catch (RecursoNoEncontradoEx e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiRespuesta(e.getMessage(), null));
		}
	}
	
	@PutMapping("/carrito/{carritoId}/item/{productoId}/actualiza")
	public ResponseEntity<ApiRespuesta> actualizaCantidadDeItem(@PathVariable Long carritoId,
																@PathVariable Long productoId, 
																@RequestParam Integer cantidad){
		try {
			carritoItemServicio.actualizaCantidadItem(carritoId, productoId, cantidad);
			return ResponseEntity.ok(new ApiRespuesta("item actualizado con exito!", null));
		} catch (RecursoNoEncontradoEx e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiRespuesta(e.getMessage(), null));
		}
	}
}