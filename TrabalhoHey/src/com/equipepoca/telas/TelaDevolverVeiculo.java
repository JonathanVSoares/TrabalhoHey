package com.equipepoca.telas;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

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
			public void actionPerformed(ActionEvent e) {
				int[] linhasSelecionadas = tabelaVeiculosLocados.getSelectedRows();

				for (int i = 0; i < linhasSelecionadas.length; i++) {
					tabela.devolverVeiculoAt(linhasSelecionadas[i]);
				}
			}
		});
	}
}
