package com.equipepoca.exception;

public class CampoPreenchidoIncorretamente extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = -6802496306623379108L;

	public CampoPreenchidoIncorretamente(){
		super("Preencha todos os campos corretamente.");
	}
}
