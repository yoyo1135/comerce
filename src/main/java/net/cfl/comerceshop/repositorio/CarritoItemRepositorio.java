package net.cfl.comerceshop.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;

import net.cfl.comerceshop.modelo.CarritoItem;

public interface CarritoItemRepositorio extends JpaRepository<CarritoItem, Long> {
	void deleteAllByCarritoId(Long id);
}
