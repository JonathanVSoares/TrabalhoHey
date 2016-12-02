package com.equipepoca.veiculo;

import com.equipepoca.cliente.Cliente;
import com.equipepoca.locacao.Locacao;

import java.time.LocalDate;
import java.util.Calendar;

/**
 *
 * @author amanda.naito
 */
public abstract class Veiculo implements VeiculoI {
	private int id;
	private final Marca marca;
	private Estado estado;
	private Locacao locacao;
	private final Categoria categoria;
	private final double valorDeCompra;
	private final String placa;
	private final int ano;

	public Veiculo(Marca marca, Estado estado, Locacao locacao, Categoria categoria, double valorDeCompra, String placa,
			int ano) {
		this.marca = marca;
		this.estado = estado;
		this.locacao = locacao;
		this.categoria = categoria;
		this.valorDeCompra = valorDeCompra;
		this.placa = placa;
		this.ano = ano;
	}

	@Override
	public void locar(int dias, Calendar data, Cliente cliente) {
		locacao = new Locacao(dias, getValorDiariaLocacao() * dias, data, cliente);
		estado = Estado.LOCADO;
	}

	@Override
	public void vender() {
		estado = Estado.VENDIDO;
	}

	@Override
	public void devolver() {
		locacao = null;
		estado = Estado.DISPONIVEL;
	}

	protected void setId(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}

	public Marca getMarca() {
		return marca;
	}

	public Estado getEstado() {
		return estado;
	}

	public Locacao getLocacao() {
		return locacao;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public String getPlaca() {
		return placa;
	}

	public int getAno() {
		return ano;
	}

	public double getValorParaVenda() {
		int idadeVeiculoEmAnos = LocalDate.now().getYear() - ano;
		double valorParaVenda = valorDeCompra - idadeVeiculoEmAnos * 0.15 * valorDeCompra;

		if (valorParaVenda < valorDeCompra * 0.1)
			valorParaVenda = valorDeCompra * 0.1;

		return valorParaVenda;
	}

	public abstract ModeloVeiculo getModelo();
}
