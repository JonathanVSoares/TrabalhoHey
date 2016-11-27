package com.equipepoca.telas;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import com.equipepoca.tabelas.TabelaVeiculosLocados;
import com.equipepoca.veiculo.Veiculo;
import com.equipepoca.veiculo.VeiculoDAO;
import com.equipepoca.veiculo.VeiculoDAOImpl;

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

		List<Veiculo> listaVeiculosLocados = new VeiculoDAOImpl().listarLocados();

		tabela = new TabelaVeiculosLocados(listaVeiculosLocados);
		tabelaVeiculosLocados = new JTable(tabela);

		devolverVeiculo = new JButton("Excluir Cliente");

		setSize(600, 600);
		setLocation(50, 50);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setViewportView(tabelaVeiculosLocados);

		add(devolverVeiculo, BorderLayout.SOUTH);

		devolverVeiculo.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				VeiculoDAO dao = new VeiculoDAOImpl();
				int[] linhasSelecionadas = tabelaVeiculosLocados.getSelectedRows();
				
				List<Veiculo> listaExcluir = new ArrayList<Veiculo>();
				for (int i = 0; i < linhasSelecionadas.length; i++) {
					Veiculo veiculo = tabela.getVeiculo(linhasSelecionadas[i]);
					dao.devolver(veiculo);
					listaExcluir.add(veiculo);

				}

				for (Veiculo veiculo : listaExcluir) {
					tabela.removeVeiculo(veiculo);
				}
			}
		});
	}
}
