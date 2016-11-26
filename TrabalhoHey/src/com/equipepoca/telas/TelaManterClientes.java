/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.equipepoca.telas;

import com.equipepoca.cliente.Cliente;
import com.equipepoca.cliente.ClienteDAOImpl;
import com.equipepoca.tabelas.TabelaManterClientes;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

/**
 *
 * @author amanda.naito
 */
public class TelaManterClientes extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = -223478523655920021L;

	private final JTable tabelaClientes;
	private final TabelaManterClientes tabela;

	private final JButton incluirCliente;
	private final JButton excluirCliente;

	public TelaManterClientes() {
		super("Manter Clientes");
		setTitle("Manter Clientes");

		tabela = new TabelaManterClientes(new ClienteDAOImpl().listarClientes());
		tabelaClientes = new JTable(tabela);

		incluirCliente = new JButton("Incluir Cliente");
		excluirCliente = new JButton("Excluir Cliente");

		setSize(600, 600);
		setLocation(50, 50);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setViewportView(tabelaClientes);
		// Adiciona o scrollPane com a tabela no centro do layout
		add(scrollPane, BorderLayout.CENTER);

		JPanel jPaneBotoes = new JPanel(new GridLayout(1, 2));
		jPaneBotoes.add(incluirCliente);
		jPaneBotoes.add(excluirCliente);
		add(jPaneBotoes, BorderLayout.SOUTH);

		incluirCliente.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Cliente cliente = new Cliente();
				tabela.adicionaCliente(cliente);
			}
		});

		excluirCliente.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ClienteDAOImpl dao = new ClienteDAOImpl();
				int[] linhasSelecionadas = tabelaClientes.getSelectedRows();
				List<Cliente> listaExcluir = new ArrayList<Cliente>();
				for (int i = 0; i < linhasSelecionadas.length; i++) {
					Cliente contato = tabela.getCliente(linhasSelecionadas[i]);
					dao.excluir(contato);
					listaExcluir.add(contato);

				}

				for (Cliente cliente : listaExcluir) {
					tabela.removeCliente(cliente);
				}
			}
		});

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public static void main(String[] args) {
		new TelaManterClientes().setVisible(true);
	}
}
