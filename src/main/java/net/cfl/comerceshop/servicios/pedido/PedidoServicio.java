package net.cfl.comerceshop.servicios.pedido;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import net.cfl.comerceshop.dto.PedidoDto;
import net.cfl.comerceshop.enums.EstadoPedido;
import net.cfl.comerceshop.excepciones.RecursoNoEncontradoEx;
import net.cfl.comerceshop.modelo.Carrito;
import net.cfl.comerceshop.modelo.Pedido;
import net.cfl.comerceshop.modelo.PedidoItem;
import net.cfl.comerceshop.repositorio.PedidoRepositorio;
import net.cfl.comerceshop.repositorio.ProductoRepositorio;
import net.cfl.comerceshop.servicios.carrito.CarritoServicio;

@Service
@RequiredArgsConstructor
public class PedidoServicio implements IPedidoServicio {

	private final PedidoRepositorio pedidoRepositorio;
	private final ProductoRepositorio productoRepositorio;
	private final CarritoServicio carritoServicio;
	private final ModelMapper modelMapper;
	
	@Transactional
	@Override
	public Pedido realizaPedido(Long usuarioId) {
		Carrito carrito = carritoServicio.traeCarritoPorUsuarioId(usuarioId);
		Pedido orden = crearPedido(carrito);
		List<PedidoItem> listaDeItemsOrden =  crearPedidoItem(orden, carrito);
		
		orden.setOrdenItems(new HashSet<>(listaDeItemsOrden));
		orden.setMontoTotal(calculaMontoTotal(listaDeItemsOrden));
		Pedido ordenGuardada = pedidoRepositorio.save(orden);
		carritoServicio.limpiaCarrito(carrito.getId());
		
		return ordenGuardada;
	}
	
	
	private List<PedidoItem> crearPedidoItem(Pedido pedido, Carrito carrito){
		return carrito.getCarritoItems().stream().map(carritoItem -> {
			Producto producto = carritoItem.getProducto();
			producto.setStock(producto.getStock() - carritoItem.getCantidad());
			productoRepositorio.save(producto);
			return new OrdenItem(
					orden, 
					producto, 
					carritoItem.getCantidad(), 
					carritoItem.getPrecioUni());
		}).toList();
	} 
	
	private Pedido crearPedido(Carrito carrito) {
		Pedido orden = new Pedido();
		orden.setUsuario(carrito.getUsuario());
		orden.setPedidoEstado(EstadoPedido.PENDIENTE);
		orden.setOrdenFecha(LocalDate.now());
		return orden;
	}
	
	
	private BigDecimal calculaMontoTotal(List<PedidoItem> listaDeItems) {
		return listaDeItems
				.stream()
				.map(item -> item.getPrecio()
						.multiply(new BigDecimal(item.getCantidad())))
				.reduce(BigDecimal.ZERO, BigDecimal :: add);
		
	}
	

	@Override
	public PedidoDto traePedido(Long ordenId) {
		return pedidoRepositorio.findById(ordenId)
				.map(this :: convertirAOrdenDto)
				.orElseThrow(() -> new RecursoNoEncontradoEx("Orden no encontrada"));
	}
	
	@Override
	public List<PedidoDto> traeUsuarioPedidos(Long usuarioId){
		List<Pedido> ordenes = pedidoRepositorio.findByUsuarioId(usuarioId);
		return ordenes.stream().map(this :: convertirAOrdenDto).toList();
	}

	private PedidoDto convertirAOrdenDto(Pedido pedido) {
		return modelMapper.map(pedido, PedidoDto.class);
	} 

}
