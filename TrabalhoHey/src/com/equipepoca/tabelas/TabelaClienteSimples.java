package com.equipepoca.tabelas;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import com.equipepoca.cliente.Cliente;
import com.equipepoca.cliente.ClienteDAOImpl;

public class TabelaClienteSimples extends AbstractTableModel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 3874605235160812234L;

	private final String[] colunas = { "CPF", "Nome", "Sobrenome" };

	private List<Cliente> listaFiltrada = new ArrayList<>();
	private List<Cliente> lista;

	public TabelaClienteSimples() {
		lista = new ClienteDAOImpl().listarClientes();
		listaFiltrada.addAll(lista);
	}

	@Override
	public int getRowCount() {
		return listaFiltrada.size();
	}

	@Override
	public int getColumnCount() {
		return colunas.length;
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
		Cliente cliente = listaFiltrada.get(rowIndex);
		switch (columnIndex) {
		case 0:
			return cliente.getCpf();
		case 1:
			return cliente.getNome();
		case 2:
			return cliente.getSobreNome();
		default:
			return null;
		}
	}

	public Cliente getClienteAt(int linha) {
		return listaFiltrada.get(linha);
	}

	public void resetFilters() {
		listaFiltrada = new ArrayList<>();
		listaFiltrada.addAll(lista);
	}

	public void filtrarByCPF(String cpf) {
		listaFiltrada.removeIf(cliente -> !cliente.getCpf().toUpperCase().contains(cpf.toUpperCase()));
	}

	public void filtrarByNome(String nome) {
		listaFiltrada.removeIf(cliente -> !cliente.getNome().toUpperCase().contains(nome.toUpperCase()));
	}

	public void filtrarBySobrenome(String sobrenome) {
		listaFiltrada.removeIf(cliente -> !cliente.getSobreNome().toUpperCase().contains(sobrenome.toUpperCase()));
	}
	
	public void updateTable(){
		fireTableRowsDeleted(0, getRowCount());
	}
}
