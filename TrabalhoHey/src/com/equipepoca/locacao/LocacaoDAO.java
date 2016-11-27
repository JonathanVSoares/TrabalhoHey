package com.equipepoca.locacao;

public interface LocacaoDAO {
	public void incluir(Locacao locacao);
	public void excluir(Locacao locacao);
	public Locacao getById(int id);
}
