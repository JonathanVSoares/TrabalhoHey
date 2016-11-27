package com.equipepoca.veiculo;

import java.util.List;

public interface VeiculoDAO {
	public void incluir(Veiculo veiculo);
	public void gravarLocacao(Veiculo veiculo);
	public void devolver(Veiculo veiculo);
	public void vender(Veiculo veiculo);
	public List<Veiculo> listarLocados();
	public List<Veiculo> listarDisponiveis();
}
