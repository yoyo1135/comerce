package net.cfl.comerceshop.request;

import java.math.BigDecimal;

import net.cfl.comerceshop.modelo.Categoria;

public class ActualizaProductoReq {
	
	private Long id;
	private String nombre;
	private String marca;
	private String descripcion;
	private BigDecimal precio;
	private int stock;
	private Categoria categoria;
}
