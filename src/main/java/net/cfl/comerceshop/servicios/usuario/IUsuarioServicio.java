package net.cfl.comerceshop.servicios.usuario;

import net.cfl.comerceshop.dto.UsuarioDto;
import net.cfl.comerceshop.modelo.Usuario;
import net.cfl.comerceshop.request.ActualizaUsuarioReq;
import net.cfl.comerceshop.request.AgregaUsuarioReq;

public interface IUsuarioServicio {
	Usuario traeUsuarioPorId(Long usuarioId);
	Usuario crearUsuario(AgregaUsuarioReq request);
	Usuario actualizarUsuario(ActualizaUsuarioReq request, Long usuarioId);
	void borrarUsuario(Long usuarioId);
	UsuarioDto convertirAUsuarioDto(Usuario usuario);
}
