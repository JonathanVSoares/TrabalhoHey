package com.equipepoca.exception;

public class LinhaNaoSelecionadaException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6528106975935756021L;
	
	public LinhaNaoSelecionadaException(){
		super("Selecione uma linha.");
	}
	
	public LinhaNaoSelecionadaException(String message){
		super(message);
	}
}
