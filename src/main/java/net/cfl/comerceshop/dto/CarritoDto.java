package net.cfl.comerceshop.dto;

import java.math.BigDecimal;
import java.util.Set;

import lombok.Data;

@Data
public class CarritoDto {
	private Long carritoId;
	private BigDecimal costoTotal;
	private Set<CarritoItemDto> carritoItems;
}

