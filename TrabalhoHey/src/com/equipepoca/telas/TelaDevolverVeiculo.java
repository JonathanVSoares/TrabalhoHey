package com.equipepoca.telas;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import com.equipepoca.tabelas.TabelaVeiculosLocados;

public class TelaDevolverVeiculo extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = -8183880558167648162L;

	private final JTable tabelaVeiculosLocados;
	private final TabelaVeiculosLocados tabela;

	private final JButton devolverVeiculo;

	public TelaDevolverVeiculo() {
		super("Devolver Veiculo");

		tabela = new TabelaVeiculosLocados();
		tabelaVeiculosLocados = new JTable(tabela);

		devolverVeiculo = new JButton("Devolver Veiculo");

		setSize(600, 600);
		setLocation(50, 50);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setViewportView(tabelaVeiculosLocados);

		add(scrollPane, BorderLayout.CENTER);
		add(devolverVeiculo, BorderLayout.SOUTH);

		devolverVeiculo.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int[] linhasSelecionadas = tabelaVeiculosLocados.getSelectedRows();
				
				for (int i = 0; i < linhasSelecionadas.length; i++) {
					tabela.devolverVeiculoAt(linhasSelecionadas[i]);
				}
			}
		});

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public static void main(String[] args){
		new TelaDevolverVeiculo().setVisible(true);
	}
}
