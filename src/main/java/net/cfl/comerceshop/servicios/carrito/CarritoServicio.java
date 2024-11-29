package net.cfl.comerceshop.servicios.carrito;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import net.cfl.comerceshop.excepciones.RecursoNoEncontradoEx;
import net.cfl.comerceshop.modelo.Carrito;
import net.cfl.comerceshop.modelo.CarritoItem;
import net.cfl.comerceshop.modelo.Usuario;
import net.cfl.comerceshop.repositorio.CarritoItemRepositorio;
import net.cfl.comerceshop.repositorio.CarritoRepositorio;

@Service
@RequiredArgsConstructor
public class CarritoServicio implements ICarritoServicio{
	private final CarritoRepositorio carritoRepositorio;
	private final CarritoItemRepositorio carritoItemRepositorio;
	private final AtomicLong generadorId = new AtomicLong(0);
	@Override
	public Carrito traeCarrito(Long id) {
		Carrito carrito = carritoRepositorio.findById(id)
				.orElseThrow(() -> new RecursoNoEncontradoEx("Carrito No Encontrado"));
		BigDecimal montoTotal = carrito.getCostoTotal();
		carrito.setCostoTotal(montoTotal);
		return carritoRepositorio.save(carrito);
	}

	@Transactional
	@Override
	public void limpiaCarrito(Long id) {
		Carrito carrito = traeCarrito(id);		
		carritoItemRepositorio.deleteAllByCarritoId(id);
		carrito.getCarritoItems().clear();
		carritoRepositorio.deleteById(id);
	}

	@Override
	public BigDecimal traePrecioTotal(Long id) {
		Carrito carrito = traeCarrito(id);
		return carrito.getCostoTotal();
	}
	
	@Override
	public Carrito inicializaCarrito(Usuario usuario) {
		return Optional.ofNullable(traeCarritoPorUsuarioId(usuario.getId()))
				.orElseGet(() -> {
					Carrito carrito = new Carrito();
					carrito.setUsuario(usuario);
					return carritoRepositorio.save(carrito);
				});
	}
	
	@Override
	public Carrito traeCarritoPorUsuarioId(Long usuarioId) {
		return carritoRepositorio.findByUsuarioId(usuarioId);
	}
	

}
