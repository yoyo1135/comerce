package net.cfl.comerceshop.dto;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class CarritoItemDto {
	private Long CarritoItemId;
	private Integer cantidad;
	private BigDecimal precioUni;
	private ProductoDto producto;
	
}