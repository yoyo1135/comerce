package net.cfl.comerceshop.repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import net.cfl.comerceshop.modelo.Pedido;

public interface PedidoRepositorio extends JpaRepository<Pedido, Long> {
	List<Pedido> findByUsuarioId(Long usuarioId);
}
