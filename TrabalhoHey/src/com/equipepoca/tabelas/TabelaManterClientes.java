/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.equipepoca.tabelas;

import com.equipepoca.cliente.Cliente;
import com.equipepoca.cliente.ClienteDAOImpl;

import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author amanda.naito
 */
public class TabelaManterClientes extends AbstractTableModel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 3874605235160812234L;

	private final String[] colunas = { "Nome", "Sobre nome", "RG", "CPF", "Endereco" };

	private List<Cliente> lista = new ArrayList<>();

	public TabelaManterClientes(List<Cliente> lista) {
		this.lista = lista;
	}

	public TabelaManterClientes() {
	}

	@Override
	public int getRowCount() {
		return lista.size();
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
		return true;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		Cliente cliente = lista.get(rowIndex);
		switch (columnIndex) {
		case 0:
			return cliente.getNome();
		case 1:
			return cliente.getSobreNome();
		case 2:
			return cliente.getRg();
		case 3:
			return cliente.getCpf();
		case 4:
			return cliente.getEndereco();
		default:
			return null;
		}
	}

	@Override
	public void setValueAt(Object value, int row, int col) {
		Cliente cliente = lista.get(row);
		switch (col) {
		case 0:
			cliente.setNome((String) value);
			break;
		case 1:
			cliente.setSobreNome((String) value);
			break;
		case 2:
			cliente.setRg((String) value);
			break;
		case 3:
			cliente.setCpf((String) value);
			break;
		case 4:
			cliente.setEndereco((String) value);
			break;
		default:
		}

		if (cliente.getId() == 0) {
			ClienteDAOImpl dao = new ClienteDAOImpl();
			dao.incluir(cliente);
		} else {
			ClienteDAOImpl dao = new ClienteDAOImpl();
			dao.atualizar(cliente);
		}

		fireTableCellUpdated(row, col);
	}

	public boolean removeCliente(Cliente cliente) {
		int linha = lista.indexOf(cliente);
		boolean result = lista.remove(cliente);
		fireTableRowsDeleted(linha, linha);
		return result;
	}

	public void adicionaCliente(Cliente cliente) {
		lista.add(cliente);
		fireTableRowsInserted(lista.size() - 1, lista.size() - 1);
	}

	public Cliente getCliente(int linha) {
		return lista.get(linha);
	}
}
