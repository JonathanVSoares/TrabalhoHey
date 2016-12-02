package com.equipepoca.telas;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;

import com.equipepoca.exception.LinhaNaoSelecionadaException;
import com.equipepoca.tabelas.TabelaVeiculosLocados;

public class TelaDevolverVeiculo extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = -8183880558167648162L;

	private final JTable tabelaVeiculosLocados;
	private final TabelaVeiculosLocados tabela;

	private final JButton devolverVeiculo;

	public TelaDevolverVeiculo() {
		super(new GridBagLayout());

		tabela = new TabelaVeiculosLocados();
		tabelaVeiculosLocados = new JTable(tabela);
		tabelaVeiculosLocados.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		devolverVeiculo = new JButton("Devolver Veiculo");

		GridBagConstraints constraints = new GridBagConstraints();

		constraints.anchor = GridBagConstraints.LINE_START;
		constraints.insets = new Insets(10, 10, 10, 10);

		constraints.gridy = 0;
		constraints.gridx = 0;
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setViewportView(tabelaVeiculosLocados);
		scrollPane.setPreferredSize(new Dimension(800, 200));
		add(scrollPane, constraints);

		constraints.anchor = GridBagConstraints.LAST_LINE_END;
		constraints.gridy = 1;
		devolverVeiculo.setPreferredSize(new Dimension(150, 20));
		add(devolverVeiculo, constraints);

		devolverVeiculo.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				try {
					int linhasSelecionada = tabelaVeiculosLocados.getSelectedRow();
	
					tabela.devolverVeiculoAt(linhasSelecionada);
				} catch (LinhaNaoSelecionadaException e){
					JOptionPane.showMessageDialog(getParent(), e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
	}
}
