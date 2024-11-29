package net.cfl.comerceshop.servicios.carrito;

import java.math.BigDecimal;

import net.cfl.comerceshop.modelo.Carrito;
import net.cfl.comerceshop.modelo.Usuario;

public interface ICarritoServicio {
	Carrito traeCarrito(Long id);
	void limpiaCarrito(Long id);
	BigDecimal traePrecioTotal(Long id);
	Carrito inicializaCarrito(Usuario usuario);
	Carrito traeCarritoPorUsuarioId(Long usuarioId);
}