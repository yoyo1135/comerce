package net.cfl.comerceshop.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;

import net.cfl.comerceshop.modelo.Categoria;

public interface CategoriaRepositorio extends JpaRepository<Categoria, Long>{
	//Categoria findByAtCategoria(String nombre);
	Categoria findByNombre(String nombre);
	boolean existsByNombre(String nombre);
}
