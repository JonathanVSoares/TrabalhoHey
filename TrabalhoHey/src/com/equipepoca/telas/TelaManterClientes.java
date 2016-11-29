package com.equipepoca.telas;

import com.equipepoca.tabelas.TabelaManterClientes;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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

		tabela = new TabelaManterClientes();
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
				tabela.adicionaCliente();
			}
		});

		excluirCliente.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int[] linhasSelecionadas = tabelaClientes.getSelectedRows();

				for (int linha : linhasSelecionadas) {
					tabela.removeClienteAt(linha);
				}
			}
		});

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public static void main(String[] args) {
		new TelaManterClientes().setVisible(true);
	}
}
