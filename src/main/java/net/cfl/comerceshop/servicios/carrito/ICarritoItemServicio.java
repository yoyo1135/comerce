package net.cfl.comerceshop.servicios.carrito;


import net.cfl.comerceshop.modelo.CarritoItem;

public interface ICarritoItemServicio {
	void agregaItemAlCarrito(Long carritoId, Long productoId, int cantidad);
	void quitaItemDelCarrito(Long carritoId, Long productoId);
	void actualizaCantidadItem(Long carritoId, Long productoId, int cantidad);
	CarritoItem traeCarritoItem(Long carritoId, Long productoId);
}
