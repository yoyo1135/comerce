package net.cfl.comerceshop.modelo;

import java.sql.Blob;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity

public class Imagen {
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private Long id ;
	private String archivoNombre;
	private String archivoTipo;
	
	@Lob
	private Blob imagen;
	private String descargaUrl;
	
	@ManyToOne //muchos a uno
	@JoinColumn(name = "prodcuto_id")
	private Producto producto;
	
}
