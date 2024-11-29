package net.cfl.comerceshop.servicios.carrito;

import java.math.BigDecimal;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import net.cfl.comerceshop.excepciones.RecursoNoEncontradoEx;
import net.cfl.comerceshop.modelo.Carrito;
import net.cfl.comerceshop.modelo.CarritoItem;
import net.cfl.comerceshop.modelo.Producto;
import net.cfl.comerceshop.repositorio.CarritoItemRepositorio;
import net.cfl.comerceshop.repositorio.CarritoRepositorio;
import net.cfl.comerceshop.servicios.producto.IProductoServicio;

@RequiredArgsConstructor
@Service
public class CarritoItemServicio implements ICarritoItemServicio{
	private final CarritoItemRepositorio carritoItemRepositorio;
	private final CarritoRepositorio carritoRepositorio;
	private final IProductoServicio productoServicio;
	private final ICarritoServicio carritoServicio;

	@Override
	public void agregaItemAlCarrito(Long carritoId, Long productoId, int cantidad) {
		Carrito carrito = carritoServicio.traeCarrito(carritoId);
		Producto producto = productoServicio.listaProductoPorId(productoId);
		CarritoItem carritoItem = carrito.getCarritoItems()
				.stream()
				.filter(item -> item.getProducto().getId().equals(productoId))
				.findFirst().orElse(new CarritoItem());
		if (carritoItem.getId() == null) {
			carritoItem.setCarrito(carrito);
			carritoItem.setProducto(producto);
			carritoItem.setCantidad(cantidad);
			carritoItem.setPrecioUni(producto.getPrecio());
			
		} else {
			carritoItem.setCantidad(carritoItem.getCantidad() + cantidad);
		}
		carritoItem.setPrecioTot();
		carrito.agregaItem(carritoItem);
		carritoItemRepositorio.save(carritoItem);
		carritoRepositorio.save(carrito);
	}

	@Override
	public void quitaItemDelCarrito(Long carritoId, Long productoId) {
		Carrito carrito = carritoServicio.traeCarrito(carritoId);
		CarritoItem itemARemover = traeCarritoItem(carritoId, productoId); 
		carrito.quitaItem(itemARemover);
		carritoRepositorio.save(carrito);
	}

	@Override
	public void actualizaCantidadItem(Long carritoId, Long productoId, int cantidad) {
		Carrito carrito = carritoServicio.traeCarrito(carritoId);
		carrito.getCarritoItems()
			.stream()
			.filter(item -> item.getProducto().getId().equals(productoId))
			.findFirst()
			.ifPresent(item -> {
				item.setCantidad(cantidad);
				item.setPrecioUni(item.getProducto().getPrecio());
				item.setPrecioTot();
			});
		
		BigDecimal montoTotal = carrito.getCarritoItems()
				.stream()
				.map(CarritoItem :: getPrecioTot)
				.reduce(BigDecimal.ZERO, BigDecimal :: add);
		carrito.setCostoTotal(montoTotal);
		carritoRepositorio.save(carrito);
	}
	@Override
	public CarritoItem traeCarritoItem(Long carritoId, Long productoId) {
		Carrito carrito = carritoServicio.traeCarrito(carritoId);
		return carrito.getCarritoItems()
				.stream()
				.filter(item -> item.getProducto().getId().equals(productoId))
				.findFirst()
				.orElseThrow(() -> new RecursoNoEncontradoEx("Produco no encontrado"));
	}

}

