package net.cfl.comerceshop.servicios.imagen;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import net.cfl.comerceshop.dto.ImagenDto;
import net.cfl.comerceshop.modelo.Imagen;

public interface IImagenServicio {
	Imagen listaImagenPorId(Long id);
	void borraImagenPorId(Long id);
	List<ImagenDto> guardaImagenes(List<MultipartFile> archivo, Long productoId);
	void actualizaImagen(MultipartFile archivo, Long imagenId);
}

