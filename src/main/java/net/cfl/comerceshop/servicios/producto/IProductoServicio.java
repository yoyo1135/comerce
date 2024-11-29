package net.cfl.comerceshop.servicios.producto;

import java.util.List;

import net.cfl.comerceshop.dto.ProductoDto;
import net.cfl.comerceshop.modelo.Producto;
import net.cfl.comerceshop.request.ActualizaProductoReq;
import net.cfl.comerceshop.request.AgregaProductoReq;

public interface IProductoServicio {

	Producto agregaProducto(AgregaProductoReq request);
	Producto listaProductoPorId(Long id);
	void borrarProducto(Long id);
	Producto actualizaProducto(ActualizaProductoReq request, Long id);
	
	List<Producto> listarProductos();
	List<Producto> listarPorCategoria(String categoria);
	List<Producto> listarPorMarca(String marca);
	List<Producto> listarPorMarcaYCategoria(String marca, String categoria);
	List<Producto> listarPorNombre(String nombre);
	List<Producto> listarPorNombreYMarca(String nombre, String marca);
	Long contarProductosPorNombreYMarca(String nombre, String marca);
	
	ProductoDto convertirAProductoDto(Producto producto);
	List<ProductoDto> traeProductosConvertidos(List<Producto> productos);
	
	
}
