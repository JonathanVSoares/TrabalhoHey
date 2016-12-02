package com.equipepoca.telas;

import com.equipepoca.exception.ClienteBloqueadoExclusaoException;
import com.equipepoca.exception.LinhaNaoSelecionadaException;
import com.equipepoca.tabelas.TabelaManterClientes;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

/**
 *
 * @author amanda.naito
 */
public class TelaManterClientes extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = -223478523655920021L;

	private final JTable tabelaClientes;
	private final TabelaManterClientes tabela;

	private final JButton incluirCliente;
	private final JButton excluirCliente;

	public TelaManterClientes() {
		super(new BorderLayout());

		tabela = new TabelaManterClientes();
		tabelaClientes = new JTable(tabela);

		incluirCliente = new JButton("Incluir Cliente");
		excluirCliente = new JButton("Excluir Cliente");

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setViewportView(tabelaClientes);
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
			public void actionPerformed(ActionEvent event) {
				try {
					int linha = tabelaClientes.getSelectedRow();
					tabela.removeClienteAt(linha);
				} catch (ClienteBloqueadoExclusaoException e) {
					JOptionPane.showMessageDialog(getParent(), e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
				} catch (LinhaNaoSelecionadaException e) {
					JOptionPane.showMessageDialog(getParent(), e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
	}
}
