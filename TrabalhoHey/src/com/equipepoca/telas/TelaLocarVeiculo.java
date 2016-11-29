package com.equipepoca.telas;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.text.MaskFormatter;

import com.equipepoca.cliente.Cliente;
import com.equipepoca.tabelas.TabelaClienteSimples;
import com.equipepoca.tabelas.TabelaVeiculosDisponiveisLocacao;
import com.equipepoca.veiculo.Categoria;
import com.equipepoca.veiculo.Marca;
import com.equipepoca.veiculo.TipoVeiculo;

public class TelaLocarVeiculo extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7485148122304664786L;

	private final JTable tabelaCliente;
	private final TabelaClienteSimples tabelaClienteSimples;

	private final JTable tabelaVeiculosDisponiveis;
	private final TabelaVeiculosDisponiveisLocacao tabelaVeiculos;

	private final JLabel labelNomeCliente;
	private final JLabel labelSobrenomeCliente;
	private final JLabel labelCPFCliente;

	private final JLabel labelTipoVeiculo;
	private final JLabel labelMarca;
	private final JLabel labelCategoria;

	private final JLabel labelDias;
	private final JLabel labelDataLocacao;

	private final JTextField jTNomeCliente;
	private final JTextField jTSobrenomeCliente;
	private final JTextField jTCPFCliente;

	private final JComboBox<TipoVeiculo> jCBTipoVeiculo;
	private final JComboBox<Marca> jCBMarca;
	private final JComboBox<Categoria> jCBCategoria;

	private final JTextField jTDias;
	private final JFormattedTextField jTDataLocacao;

	private final JButton btnLocarVeiculo;

	private final DateFormat dateFormat;

	public TelaLocarVeiculo() {
		super("Locar Veiculo");

		tabelaClienteSimples = new TabelaClienteSimples();
		tabelaCliente = new JTable(tabelaClienteSimples);
		tabelaCliente.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		tabelaVeiculos = new TabelaVeiculosDisponiveisLocacao();
		tabelaVeiculosDisponiveis = new JTable(tabelaVeiculos);
		tabelaVeiculosDisponiveis.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		labelNomeCliente = new JLabel("Nome:");
		labelSobrenomeCliente = new JLabel("Sobrenome:");
		labelCPFCliente = new JLabel("CPF:");

		labelTipoVeiculo = new JLabel("Tipo do Veiculo:");
		labelMarca = new JLabel("Marca:");
		labelCategoria = new JLabel("Categoria:");

		labelDias = new JLabel("Dias:");
		labelDataLocacao = new JLabel("Data de locação:");

		jTNomeCliente = new JTextField();
		jTSobrenomeCliente = new JTextField();
		jTCPFCliente = new JTextField();

		jCBTipoVeiculo = new JComboBox<TipoVeiculo>(TipoVeiculo.values());
		jCBTipoVeiculo.insertItemAt(null, 0);
		jCBMarca = new JComboBox<Marca>(Marca.values());
		jCBMarca.insertItemAt(null, 0);
		jCBCategoria = new JComboBox<Categoria>(Categoria.values());
		jCBCategoria.insertItemAt(null, 0);

		dateFormat = new SimpleDateFormat("MM/dd/yyyy");

		jTDias = new JTextField();
		jTDataLocacao = new JFormattedTextField(dateFormat);

		btnLocarVeiculo = new JButton("Locar Veiculo");

		try {
			MaskFormatter placaMask = new MaskFormatter("##/##/####");
			placaMask.setPlaceholderCharacter('_');
			placaMask.install(jTDataLocacao);
		} catch (ParseException ex) {
		}

		getContentPane().setLayout(null);

		setSize(600, 600);
		setLocation(50, 50);

		labelNomeCliente.setBounds(10, 10, 110, 20);
		jTNomeCliente.setBounds(120, 10, 140, 20);
		add(labelNomeCliente);
		add(jTNomeCliente);

		labelSobrenomeCliente.setBounds(10, 40, 110, 20);
		jTSobrenomeCliente.setBounds(120, 40, 140, 20);
		add(labelSobrenomeCliente);
		add(jTSobrenomeCliente);

		labelCPFCliente.setBounds(10, 70, 110, 20);
		jTCPFCliente.setBounds(120, 70, 140, 20);
		add(labelCPFCliente);
		add(jTCPFCliente);

		JScrollPane scrollPaneClientes = new JScrollPane();
		scrollPaneClientes.setViewportView(tabelaCliente);

		scrollPaneClientes.setBounds(10, 100, 300, 70);
		add(scrollPaneClientes);

		labelTipoVeiculo.setBounds(10, 200, 110, 20);
		jCBTipoVeiculo.setBounds(120, 200, 140, 20);
		add(labelTipoVeiculo);
		add(jCBTipoVeiculo);

		labelMarca.setBounds(10, 230, 110, 20);
		jCBMarca.setBounds(120, 230, 140, 20);
		add(labelMarca);
		add(jCBMarca);

		labelCategoria.setBounds(10, 260, 110, 20);
		jCBCategoria.setBounds(120, 260, 140, 20);
		add(labelCategoria);
		add(jCBCategoria);

		JScrollPane scrollPaneVeiculos = new JScrollPane();
		scrollPaneVeiculos.setViewportView(tabelaVeiculosDisponiveis);

		scrollPaneVeiculos.setBounds(10, 290, 500, 120);
		add(scrollPaneVeiculos);

		labelDias.setBounds(10, 440, 110, 20);
		jTDias.setBounds(120, 440, 140, 20);
		add(labelDias);
		add(jTDias);

		labelDataLocacao.setBounds(10, 470, 110, 20);
		jTDataLocacao.setBounds(120, 470, 140, 20);
		add(labelDataLocacao);
		add(jTDataLocacao);

		btnLocarVeiculo.setBounds(10, 500, 140, 20);
		add(btnLocarVeiculo);

		jCBTipoVeiculo.setSelectedIndex(-1);
		jCBMarca.setSelectedIndex(-1);
		jCBCategoria.setSelectedIndex(-1);

		jTNomeCliente.addKeyListener(new FiltroTabelaClienteListener());
		jTSobrenomeCliente.addKeyListener(new FiltroTabelaClienteListener());
		jTCPFCliente.addKeyListener(new FiltroTabelaClienteListener());

		jCBTipoVeiculo.addActionListener(new FiltroTabelaVeiculoListener());
		jCBMarca.addActionListener(new FiltroTabelaVeiculoListener());
		jCBCategoria.addActionListener(new FiltroTabelaVeiculoListener());

		btnLocarVeiculo.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				try {
					int linhaClienteSelecionada = tabelaCliente.getSelectedRow();
					Cliente cliente = tabelaClienteSimples.getClienteAt(linhaClienteSelecionada);

					int dias = Integer.valueOf(jTDias.getText());

					Calendar dataLocacao = Calendar.getInstance();
					dataLocacao.setTime(dateFormat.parse(jTDataLocacao.getText()));

					int linhaSelecionada = tabelaVeiculosDisponiveis.getSelectedRow();
					tabelaVeiculos.locarVeiculoAt(linhaSelecionada, dias, dataLocacao, cliente);

				} catch (ParseException e) {
					e.printStackTrace();
				}
			}
		});

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	private void updateClienteFilters() {
		tabelaClienteSimples.resetFilters();

		String nomeCliente = jTNomeCliente.getText();
		String sobrenomeCliente = jTSobrenomeCliente.getText();
		String cpfCliente = jTCPFCliente.getText();

		if (!nomeCliente.equals(""))
			tabelaClienteSimples.filtrarByNome(nomeCliente);
		if (!sobrenomeCliente.equals(""))
			tabelaClienteSimples.filtrarBySobrenome(sobrenomeCliente);
		if (!cpfCliente.equals(""))
			tabelaClienteSimples.filtrarByCPF(cpfCliente);

		tabelaClienteSimples.updateTable();
	}

	private void updateVeiculoFilters() {
		tabelaVeiculos.resetFilters();

		TipoVeiculo tipoVeiculoSelecionado = jCBTipoVeiculo.getItemAt(jCBTipoVeiculo.getSelectedIndex());
		if (tipoVeiculoSelecionado != null)
			tabelaVeiculos.filterByTipoVeiculo(tipoVeiculoSelecionado);

		Marca marcaSelecionada = jCBMarca.getItemAt(jCBMarca.getSelectedIndex());
		if (marcaSelecionada != null)
			tabelaVeiculos.filterByMarca(marcaSelecionada);

		Categoria categoriaSelecionada = jCBCategoria.getItemAt(jCBCategoria.getSelectedIndex());
		if (categoriaSelecionada != null)
			tabelaVeiculos.filterByCategoria(categoriaSelecionada);

		tabelaVeiculos.updateTable();
	}

	public static void main(String[] args) {
		new TelaLocarVeiculo().setVisible(true);
	}

	private class FiltroTabelaClienteListener implements KeyListener {
		@Override
		public void keyTyped(KeyEvent e) {
		}

		@Override
		public void keyReleased(KeyEvent e) {
			updateClienteFilters();
		}

		@Override
		public void keyPressed(KeyEvent e) {
		}
	}

	private class FiltroTabelaVeiculoListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent arg0) {
			updateVeiculoFilters();
		}
	}
}
