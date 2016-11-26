package com.equipepoca.tabelas;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import com.equipepoca.veiculo.Veiculo;

public class TabelaVeiculosLocados extends AbstractTableModel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6486695824536180783L;

	private final String[] colunas = { "Nome do Cliente", "Placa", "Marca", "Modelo", "Ano", "Data Locação",
			"Preço Diária", "Dias locado", "Valor Locação" };

	private List<Veiculo> lista = new ArrayList<>();

	public TabelaVeiculosLocados(List<Veiculo> lista) {
		this.lista = lista;
	}

	public TabelaVeiculosLocados() {
	}

	@Override
	public int getColumnCount() {
		return colunas.length;
	}

	@Override
	public int getRowCount() {
		return lista.size();
	}

	@Override
	public String getColumnName(int index) {
		return colunas[index];
	}

	@Override
	public boolean isCellEditable(int row, int column) {
		return false;
	}

	@Override
	public String getValueAt(int rowIndex, int columnIndex) {
		Veiculo veiculo = lista.get(rowIndex);
		switch (columnIndex) {
		case 0:
			return veiculo.getLocacao().getCliente().getNome();
		case 1:
			return veiculo.getPlaca();
		case 2:
			return veiculo.getMarca().toString();
		case 3:
			return veiculo.getModelo().toString();
		case 4:
			return String.valueOf(veiculo.getAno());
		case 5:
			SimpleDateFormat formatData = new SimpleDateFormat("dd/MM/yyyy");
			return formatData.format(veiculo.getLocacao().getData());
		case 6:
			return String.valueOf(veiculo.getValorDiariaLocacao());
		case 7:
			return String.valueOf(veiculo.getLocacao().getDias());
		case 8:
			return String.valueOf(veiculo.getLocacao().getValor());
		default:
			return null;
		}
	}

}
