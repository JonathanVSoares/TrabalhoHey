package com.equipepoca.tabelas;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import com.equipepoca.exception.LinhaNaoSelecionadaException;
import com.equipepoca.locacao.Locacao;
import com.equipepoca.locacao.LocacaoDAO;
import com.equipepoca.locacao.LocacaoDAOImpl;
import com.equipepoca.veiculo.Veiculo;
import com.equipepoca.veiculo.VeiculoDAO;
import com.equipepoca.veiculo.VeiculoDAOImpl;

public class TabelaVeiculosLocados extends AbstractTableModel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6486695824536180783L;

	private final String[] colunas = { "Nome do Cliente", "Placa", "Marca", "Modelo", "Ano", "Data Locação",
			"Preço Diária", "Dias locado", "Valor Locação" };

	private List<Veiculo> lista = new ArrayList<>();

	public TabelaVeiculosLocados() {
		this.lista = new VeiculoDAOImpl().listarLocados();
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
			return formatData.format(veiculo.getLocacao().getData().getTime());
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

	public boolean devolverVeiculoAt(int linha) throws LinhaNaoSelecionadaException {
		if (linha < 0)
			throw new LinhaNaoSelecionadaException();

		Veiculo veiculo = lista.get(linha);

		Locacao locacao = veiculo.getLocacao();

		veiculo.devolver();
		VeiculoDAO veiculoDao = new VeiculoDAOImpl();
		veiculoDao.devolver(veiculo);

		LocacaoDAO locacaoDao = new LocacaoDAOImpl();
		locacaoDao.excluir(locacao);

		boolean result = lista.remove(veiculo);
		fireTableRowsDeleted(linha, linha);
		return result;
	}

	public Veiculo getVeiculo(int linha) {
		return lista.get(linha);
	}
}
