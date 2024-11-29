package net.cfl.comerceshop.repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import net.cfl.comerceshop.modelo.Categoria;
import net.cfl.comerceshop.modelo.Imagen;

public interface ImagenRepositorio extends JpaRepository<Imagen, Long>{
	List<Imagen> findByProductoId(Long id);

}

