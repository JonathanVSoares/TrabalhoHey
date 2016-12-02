package com.equipepoca.telas;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;

import com.equipepoca.exception.LinhaNaoSelecionadaException;
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
		super(new GridBagLayout());

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

		GridBagConstraints constraints = new GridBagConstraints();

		constraints.anchor = GridBagConstraints.LINE_START;
		constraints.insets = new Insets(10, 10, 10, 10);

		Dimension defaultDimension = new Dimension(100, 20);

		constraints.gridy = 0;
		constraints.gridx = 0;
		labelTipoVeiculo.setPreferredSize(defaultDimension);
		add(labelTipoVeiculo, constraints);
		constraints.gridx = 1;
		jCBTipoVeiculo.setPreferredSize(defaultDimension);
		add(jCBTipoVeiculo, constraints);

		constraints.gridx = 2;
		labelMarca.setPreferredSize(defaultDimension);
		add(labelMarca, constraints);
		constraints.gridx = 3;
		jCBMarca.setPreferredSize(defaultDimension);
		add(jCBMarca, constraints);

		constraints.gridx = 4;
		labelCategoria.setPreferredSize(defaultDimension);
		add(labelCategoria, constraints);
		constraints.gridx = 5;
		jCBCategoria.setPreferredSize(defaultDimension);
		add(jCBCategoria, constraints);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setViewportView(tabelaVeiculos);

		constraints.gridy = 1;
		constraints.gridx = 0;
		constraints.gridwidth = GridBagConstraints.REMAINDER;
		
		scrollPane.setPreferredSize(new Dimension(700, 300));
		add(scrollPane, constraints);


		constraints.gridy = 2;
		constraints.gridx = 0;
		constraints.anchor = GridBagConstraints.LAST_LINE_END;
		
		btnVenderVeiculo.setPreferredSize(new Dimension(140, 20));
		add(btnVenderVeiculo, constraints);

		jCBTipoVeiculo.setSelectedIndex(-1);
		jCBMarca.setSelectedIndex(-1);
		jCBCategoria.setSelectedIndex(-1);

		jCBTipoVeiculo.addActionListener(new FiltroTabelaVeiculoListener());
		jCBMarca.addActionListener(new FiltroTabelaVeiculoListener());
		jCBCategoria.addActionListener(new FiltroTabelaVeiculoListener());

		btnVenderVeiculo.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				try{
					int linhaSelecionada = tabelaVeiculos.getSelectedRow();
					tabelaVeiculosDisponiveis.venderVeiculoAt(linhaSelecionada);
				} catch (LinhaNaoSelecionadaException e){
					JOptionPane.showMessageDialog(getParent(), e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
				}
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

	private class FiltroTabelaVeiculoListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent arg0) {
			updateVeiculoFilters();
		}
	}
}
