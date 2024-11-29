package net.cfl.comerceshop.controlador;

import java.math.BigDecimal;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import net.cfl.comerceshop.excepciones.RecursoNoEncontradoEx;
import net.cfl.comerceshop.modelo.Carrito;
import net.cfl.comerceshop.respuesta.ApiRespuesta;
import net.cfl.comerceshop.servicios.carrito.ICarritoServicio;
import net.cfl.comerceshop.servicios.categoria.ICategoriaServicio;
import net.cfl.comerceshop.servicios.producto.IProductoServicio;

@RequiredArgsConstructor
@RestController
@RequestMapping("${api.prefix}/carritos")
public class CarritoControlador {
	private final ICarritoServicio carritoServicio;
	private final IProductoServicio productoServicio;
	
	@GetMapping("/{carritoId}/mi-carrito")
	public ResponseEntity<ApiRespuesta> traeCarrito (@PathVariable Long carritoId){
		try {
			Carrito carrito = carritoServicio.traeCarrito(carritoId);
			return ResponseEntity.ok(new ApiRespuesta("Exito", carrito));
		} catch (RecursoNoEncontradoEx e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiRespuesta(e.getMessage(), null));
		}
	}
	
	@DeleteMapping("/{carritoId}/limpiar")
	public ResponseEntity<ApiRespuesta> limpiaCarrito(@PathVariable Long carritoId){
		try {
			carritoServicio.limpiaCarrito(carritoId);
			return ResponseEntity.ok(new ApiRespuesta("exito!", null));
		} catch (RecursoNoEncontradoEx e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiRespuesta(e.getLocalizedMessage(),null));
		}
	}
	
	@GetMapping("/{carritoId}/carrito/monto-total")
	public ResponseEntity<ApiRespuesta> calculaMontoTotal(@PathVariable Long carritoId){
		try {
			BigDecimal montoTotal = carritoServicio.traePrecioTotal(carritoId);
			return ResponseEntity.ok(new ApiRespuesta("Monto Total: ", montoTotal));
		} catch (RecursoNoEncontradoEx e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiRespuesta(e.getLocalizedMessage(),null));
		}
	}
}