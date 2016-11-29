package com.equipepoca.telas;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;

import com.equipepoca.tabelas.TabelaVeiculosDisponiveisVenda;
import com.equipepoca.veiculo.Categoria;
import com.equipepoca.veiculo.Marca;
import com.equipepoca.veiculo.TipoVeiculo;

public class TelaVenderVeiculo extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5427106674596404210L;

	private final JTable tabelaVeiculos;
	private final TabelaVeiculosDisponiveisVenda tabelaVeiculosDisponiveis;

	private final JLabel labelTipoVeiculo;
	private final JLabel labelMarca;
	private final JLabel labelCategoria;

	private final JComboBox<TipoVeiculo> jCBTipoVeiculo;
	private final JComboBox<Marca> jCBMarca;
	private final JComboBox<Categoria> jCBCategoria;

	private final JButton btnVenderVeiculo;

	public TelaVenderVeiculo() {
		labelTipoVeiculo = new JLabel("Tipo do Veiculo:");
		labelMarca = new JLabel("Marca:");
		labelCategoria = new JLabel("Categoria:");

		jCBTipoVeiculo = new JComboBox<TipoVeiculo>(TipoVeiculo.values());
		jCBTipoVeiculo.insertItemAt(null, 0);
		jCBMarca = new JComboBox<Marca>(Marca.values());
		jCBMarca.insertItemAt(null, 0);
		jCBCategoria = new JComboBox<Categoria>(Categoria.values());
		jCBCategoria.insertItemAt(null, 0);

		tabelaVeiculosDisponiveis = new TabelaVeiculosDisponiveisVenda();
		tabelaVeiculos = new JTable(tabelaVeiculosDisponiveis);
		tabelaVeiculos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		btnVenderVeiculo = new JButton("Vender Veiculo");

		setSize(600, 600);
		setLocation(50, 50);

		labelTipoVeiculo.setBounds(10, 10, 110, 20);
		jCBTipoVeiculo.setBounds(120, 10, 140, 20);
		add(labelTipoVeiculo);
		add(jCBTipoVeiculo);

		labelMarca.setBounds(10, 40, 110, 20);
		jCBMarca.setBounds(120, 40, 140, 20);
		add(labelMarca);
		add(jCBMarca);

		labelCategoria.setBounds(10, 70, 110, 20);
		jCBCategoria.setBounds(120, 70, 140, 20);
		add(labelCategoria);
		add(jCBCategoria);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setViewportView(tabelaVeiculos);

		scrollPane.setBounds(10, 100, 500, 120);
		add(scrollPane);
		
		btnVenderVeiculo.setBounds(10, 500, 140, 20);
		add(btnVenderVeiculo);

		jCBTipoVeiculo.setSelectedIndex(-1);
		jCBMarca.setSelectedIndex(-1);
		jCBCategoria.setSelectedIndex(-1);

		jCBTipoVeiculo.addActionListener(new FiltroTabelaVeiculoListener());
		jCBMarca.addActionListener(new FiltroTabelaVeiculoListener());
		jCBCategoria.addActionListener(new FiltroTabelaVeiculoListener());
		
		btnVenderVeiculo.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int linhaSelecionada = tabelaVeiculos.getSelectedRow();
				tabelaVeiculosDisponiveis.venderVeiculoAt(linhaSelecionada);
			}
		});
	}

	private void updateVeiculoFilters() {
		tabelaVeiculosDisponiveis.resetFilters();

		TipoVeiculo tipoVeiculoSelecionado = jCBTipoVeiculo.getItemAt(jCBTipoVeiculo.getSelectedIndex());
		if (tipoVeiculoSelecionado != null)
			tabelaVeiculosDisponiveis.filterByTipoVeiculo(tipoVeiculoSelecionado);

		Marca marcaSelecionada = jCBMarca.getItemAt(jCBMarca.getSelectedIndex());
		if (marcaSelecionada != null)
			tabelaVeiculosDisponiveis.filterByMarca(marcaSelecionada);

		Categoria categoriaSelecionada = jCBCategoria.getItemAt(jCBCategoria.getSelectedIndex());
		if (categoriaSelecionada != null)
			tabelaVeiculosDisponiveis.filterByCategoria(categoriaSelecionada);

		tabelaVeiculosDisponiveis.updateTable();
	}

	public static void main(String[] args) {
		new TelaVenderVeiculo().setVisible(true);
	}

	private class FiltroTabelaVeiculoListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent arg0) {
			updateVeiculoFilters();
		}
	}
}
