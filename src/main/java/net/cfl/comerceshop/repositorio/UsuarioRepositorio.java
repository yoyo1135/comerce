package net.cfl.comerceshop.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;

import net.cfl.comerceshop.modelo.Usuario;

public interface UsuarioRepositorio extends JpaRepository<Usuario, Long> {
	boolean existsByEmail (String email);
}
