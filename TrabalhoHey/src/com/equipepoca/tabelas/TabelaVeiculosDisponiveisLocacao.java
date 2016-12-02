package com.equipepoca.tabelas;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import com.equipepoca.cliente.Cliente;
import com.equipepoca.exception.LinhaNaoSelecionadaException;
import com.equipepoca.locacao.LocacaoDAO;
import com.equipepoca.locacao.LocacaoDAOImpl;
import com.equipepoca.veiculo.Automovel;
import com.equipepoca.veiculo.Categoria;
import com.equipepoca.veiculo.Marca;
import com.equipepoca.veiculo.Motocicleta;
import com.equipepoca.veiculo.TipoVeiculo;
import com.equipepoca.veiculo.Van;
import com.equipepoca.veiculo.Veiculo;
import com.equipepoca.veiculo.VeiculoDAO;
import com.equipepoca.veiculo.VeiculoDAOImpl;

public class TabelaVeiculosDisponiveisLocacao extends AbstractTableModel {
	/**
	 * 
	 */
	private static final long serialVersionUID = -108418777027889315L;

	private final String[] colunas = { "Placa", "Marca", "Modelo", "Ano", "Preço Diária" };

	private List<Veiculo> lista;
	private List<Veiculo> listaFiltrada = new ArrayList<>();

	public TabelaVeiculosDisponiveisLocacao() {
		lista = new VeiculoDAOImpl().listarDisponiveis();
		listaFiltrada.addAll(lista);
	}

	@Override
	public int getColumnCount() {
		return colunas.length;
	}

	@Override
	public int getRowCount() {
		return listaFiltrada.size();
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
		Veiculo veiculo = listaFiltrada.get(rowIndex);
		switch (columnIndex) {
		case 0:
			return veiculo.getPlaca();
		case 1:
			return veiculo.getMarca().toString();
		case 2:
			return veiculo.getModelo().toString();
		case 3:
			return Integer.toString(veiculo.getAno());
		case 4:
			NumberFormat format = NumberFormat.getCurrencyInstance();
			return format.format(veiculo.getValorDiariaLocacao());
		default:
			return null;
		}
	}

	public boolean locarVeiculoAt(int linha, int dias, Calendar data, Cliente cliente) throws LinhaNaoSelecionadaException {
		if (linha < 0)
			throw new LinhaNaoSelecionadaException("Selecione um veículo para associar à locação");
		
		Veiculo veiculo = listaFiltrada.get(linha);
		veiculo.locar(dias, data, cliente);

		LocacaoDAO daoLocacao = new LocacaoDAOImpl();
		daoLocacao.incluir(veiculo.getLocacao());

		VeiculoDAO daoVeiculo = new VeiculoDAOImpl();
		daoVeiculo.gravarLocacao(veiculo);

		boolean result = listaFiltrada.remove(veiculo) && lista.remove(veiculo);
		fireTableRowsDeleted(linha, linha);
		return result;
	}

	public void resetFilters() {
		listaFiltrada = new ArrayList<Veiculo>();
		listaFiltrada.addAll(lista);
	}

	public void filterByTipoVeiculo(TipoVeiculo tipoVeiculo) {
		switch (tipoVeiculo) {
		case AUTOMOVEL:
			listaFiltrada.removeIf(veiculo -> !(veiculo instanceof Automovel));
			break;
		case MOTOCICLETA:
			listaFiltrada.removeIf(veiculo -> !(veiculo instanceof Motocicleta));
			break;
		case VAN:
			listaFiltrada.removeIf(veiculo -> !(veiculo instanceof Van));
			break;
		default:
			break;
		}
	}

	public void filterByMarca(Marca marca) {
		listaFiltrada.removeIf(veiculo -> !veiculo.getMarca().equals(marca));
	}

	public void filterByCategoria(Categoria categoria) {
		listaFiltrada.removeIf(veiculo -> !veiculo.getCategoria().equals(categoria));
	}
	
	public void updateTable(){
		fireTableRowsDeleted(0, getRowCount());
	}
}
