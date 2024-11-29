package net.cfl.comerceshop.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;

import net.cfl.comerceshop.modelo.Carrito;

public interface CarritoRepositorio extends JpaRepository<Carrito, Long> {
	Carrito findByUsuarioId(Long id);
}
