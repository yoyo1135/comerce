package net.cfl.comerceshop.dto;

import java.math.BigDecimal;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Data;
import net.cfl.comerceshop.modelo.Categoria;
import net.cfl.comerceshop.modelo.Imagen;

@Data
public class ProductoDto {
	private Long id;
	private String nombre;
	private String marca;
	private String descripcion;
	private BigDecimal precio;
	private int stock;
	private Categoria categoria;
	private List<ImagenDto> imagenes;
}

