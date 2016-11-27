package com.equipepoca.tabelas;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import com.equipepoca.veiculo.Veiculo;

public class TabelaVeiculosDisponiveisLocacao extends AbstractTableModel {
	/**
	 * 
	 */
	private static final long serialVersionUID = -108418777027889315L;

	private final String[] colunas = { "Placa", "Marca", "Modelo", "Ano", "Preço Diária" };

	private List<Veiculo> lista = new ArrayList<>();

	public TabelaVeiculosDisponiveisLocacao(List<Veiculo> lista) {
		this.lista = lista;
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
			return veiculo.getPlaca();
		case 1:
			return veiculo.getMarca().toString();
		case 2:
			return veiculo.getMarca().toString();
		case 3:
			return Integer.toString(veiculo.getAno());
		case 4:
			return Double.toString(veiculo.getValorDiariaLocacao());
		default:
			return null;
		}
	}

	public boolean removeVeiculo(Veiculo veiculo) {
		int linha = lista.indexOf(veiculo);
		boolean result = lista.remove(veiculo);
		fireTableRowsDeleted(linha, linha);
		return result;
	}

	public Veiculo getVeiculo(int linha) {
		return lista.get(linha);
	}
}
