package net.cfl.comerceshop.controlador;

import java.sql.SQLException;
import java.util.List;

import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import lombok.RequiredArgsConstructor;
import net.cfl.comerceshop.dto.ImagenDto;
import net.cfl.comerceshop.excepciones.RecursoNoEncontradoEx;
import net.cfl.comerceshop.modelo.Imagen;
import net.cfl.comerceshop.respuesta.ApiRespuesta;
import net.cfl.comerceshop.servicios.imagen.IImagenServicio;

@RequiredArgsConstructor
@RestController
@RequestMapping("${api.prefix}/imagenes")
public class ImagenControlador {
	private final IImagenServicio imagenServicio;	
	
	// Rest Api Imagenes
	// api/v1/imagenes/upload
	@PostMapping("/upload")
	public ResponseEntity<ApiRespuesta> guardaImagenes(@RequestParam List<MultipartFile> archivos, @RequestParam Long idProducto){
		try {
			List<ImagenDto> imagenesDto = imagenServicio.guardaImagenes(archivos, idProducto);
			return ResponseEntity.ok(new ApiRespuesta("imagen subida correctamente", imagenesDto));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiRespuesta("error al subir imagen", e.getMessage()));
		}
	}
	
	@GetMapping("/imagen/descarga/{imagenId}")
	public ResponseEntity<Resource> descargaImagen(@PathVariable Long imagenId) throws SQLException{
		Imagen imagen = imagenServicio.listaImagenPorId(imagenId);
		ByteArrayResource recurso = new ByteArrayResource(imagen.getImagen().getBytes(1, (int)imagen.getImagen().length()));
		return ResponseEntity.ok().contentType(MediaType.parseMediaType(imagen.getArchivoTipo()))
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + imagen.getArchivoNombre() + "\"")
				.body(recurso);
	}
	@PutMapping("/imagen/{imagenId}/actualiza")
	public ResponseEntity<ApiRespuesta> actualizaImagen(@PathVariable Long imagenId, @RequestBody MultipartFile archivo){
		try {
			Imagen imagen = imagenServicio.listaImagenPorId(imagenId);
			if (imagen != null) {
				imagenServicio.actualizaImagen(archivo, imagenId);
				return ResponseEntity.ok(new ApiRespuesta("Imagen actualizada!", null));
			}
		} catch (RecursoNoEncontradoEx e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body(new ApiRespuesta(e.getMessage(),null));
		}
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
				.body(new ApiRespuesta("Fallo al actualizar imagen", null));
	}
	
	@DeleteMapping("/imagen/{imagenId}/borrar")
	public ResponseEntity<ApiRespuesta> borraImagen(@PathVariable Long imagenId){
		try {
			Imagen imagen = imagenServicio.listaImagenPorId(imagenId);
			if (imagen != null) {
				imagenServicio.borraImagenPorId(imagenId);
				return ResponseEntity.ok(new ApiRespuesta("Imagen borrada!", null));
			}
		} catch (RecursoNoEncontradoEx e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body(new ApiRespuesta(e.getMessage(),null));
		}
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
				.body(new ApiRespuesta("Fallo al borrar imagen", null));
	}
}
