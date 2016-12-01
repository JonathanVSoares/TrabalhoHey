package com.equipepoca.exception;

public class ClienteBloqueadoExclusaoException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1949906320043649517L;

	public ClienteBloqueadoExclusaoException(){
		super("Cliente est� bloqueado para exclus�o");
	}

	public ClienteBloqueadoExclusaoException(String message){
		super(message);
	}
}
