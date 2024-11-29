package net.cfl.comerceshop.excepciones;

public class UsuarioExistenteEx extends RuntimeException {
	public UsuarioExistenteEx(String mensaje) {
		super(mensaje);
	}

}