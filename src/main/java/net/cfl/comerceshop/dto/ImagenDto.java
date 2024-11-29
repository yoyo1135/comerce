package net.cfl.comerceshop.dto;

import java.sql.Blob;

import lombok.Data;

@Data
public class ImagenDto {
	private Long id;
	private String archivoNombre;
	private String descargaUrl;
}
