package net.cfl.comerceshop.controlador;

import net.cfl.comerceshop.dto.PedidoDto;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import net.cfl.comerceshop.excepciones.RecursoNoEncontradoEx;
import net.cfl.comerceshop.modelo.Pedido;
import net.cfl.comerceshop.respuesta.ApiRespuesta;
import net.cfl.comerceshop.servicios.carrito.ICarritoServicio;
import net.cfl.comerceshop.servicios.pedido.IPedidoServicio;
import net.cfl.comerceshop.servicios.producto.IProductoServicio;

@RequiredArgsConstructor
@RestController
@RequestMapping("${api.prefix}/ordenes")
public class PedidoControlador {
	private final IOrdenServicio ordenServicio;
	
	@PostMapping("/orden")
	public ResponseEntity<ApiRespuesta> creaOrden(@RequestParam Long usuarioId){
		try {
			Orden orden = ordenServicio.realizaOrden(usuarioId);
			return ResponseEntity.ok(new ApiRespuesta("Exito", orden));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(new ApiRespuesta("Se ha producido un error", e.getMessage()));
		}
	}
	
	@GetMapping("/{ordenId}/orden")
	public ResponseEntity<ApiRespuesta> traeOrdenPorId(@PathVariable Long ordenId){
		try {
			OrdenDto orden = ordenServicio.traeOrden(ordenId);
			return ResponseEntity.ok(new ApiRespuesta("Exito!", orden));
		} catch (RecursoNoEncontradoEx e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiRespuesta("oops", e.getMessage()));
		}
	}
	
	@GetMapping("/{usuarioId}/orden")
	public ResponseEntity<ApiRespuesta> traeOrdenUsuario(@PathVariable Long usuarioId){
		try {
			List<OrdenDto> orden = ordenServicio.traeUsuarioOrdenes(usuarioId);
			return ResponseEntity.ok(new ApiRespuesta("Exito!", orden));
		} catch (RecursoNoEncontradoEx e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiRespuesta("oops", e.getMessage()));
		}
	}
}

