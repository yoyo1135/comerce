package net.cfl.comerceshop.dto;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class PedidoItemDto {
	private Long productoId;
	private String productoNombre;
	private int cantidad;
	private BigDecimal precio;
}
