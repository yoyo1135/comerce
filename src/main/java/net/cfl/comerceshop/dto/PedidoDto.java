package net.cfl.comerceshop.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.OneToMany;
import lombok.Data;
import net.cfl.comerceshop.enums.EstadoPedido;
import net.cfl.comerceshop.modelo.PedidoItem;

@Data
public class PedidoDto {
	private Long id;
	private Long usuarioId;
	private LocalDate ordenFecha;
	private BigDecimal montoTotal;
	private String ordenEstado;
	private List<PedidoItemDto> items;
	
}