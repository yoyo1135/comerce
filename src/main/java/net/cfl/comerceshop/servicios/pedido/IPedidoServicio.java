package net.cfl.comerceshop.servicios.pedido;


import java.util.List;

import net.cfl.comerceshop.dto.PedidoDto;
import net.cfl.comerceshop.modelo.Pedido;

public interface IPedidoServicio {
	Pedido realizaPedido(Long usuarioId);
	PedidoDto traePedido(Long ordenId);
	List<PedidoDto> traeUsuarioPedidos(Long usuarioId);
}
