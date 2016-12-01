package com.equipepoca.telas;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
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
import javax.swing.JLabel;
import javax.swing.JPanel;
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

public class TelaLocarVeiculo extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7485148122304664786L;

	private JTable tabelaCliente;
	private TabelaClienteSimples tabelaClienteSimples;

	private JTable tabelaVeiculosDisponiveis;
	private TabelaVeiculosDisponiveisLocacao tabelaVeiculos;

	private JLabel labelNomeCliente;
	private JLabel labelSobrenomeCliente;
	private JLabel labelCPFCliente;

	private JLabel labelTipoVeiculo;
	private JLabel labelMarca;
	private JLabel labelCategoria;

	private JLabel labelDias;
	private JLabel labelDataLocacao;

	private JTextField jTNomeCliente;
	private JTextField jTSobrenomeCliente;
	private JTextField jTCPFCliente;

	private JComboBox<TipoVeiculo> jCBTipoVeiculo;
	private JComboBox<Marca> jCBMarca;
	private JComboBox<Categoria> jCBCategoria;

	private JTextField jTDias;
	private JFormattedTextField jTDataLocacao;

	private JButton btnLocarVeiculo;

	private DateFormat dateFormat;

	public TelaLocarVeiculo() {
		super(new GridBagLayout());

		initializeElements();

		GridBagConstraints constraints = new GridBagConstraints();

		constraints.anchor = GridBagConstraints.LINE_START;
		constraints.insets = new Insets(10, 10, 10, 10);

		Dimension defaultDimension = new Dimension(100, 20);

		constraints.gridy = 0;

		constraints.gridx = 0;
		labelCPFCliente.setPreferredSize(defaultDimension);
		add(labelCPFCliente, constraints);
		constraints.gridx = 1;
		jTCPFCliente.setPreferredSize(defaultDimension);
		add(jTCPFCliente, constraints);

		constraints.gridx = 2;
		labelNomeCliente.setPreferredSize(defaultDimension);
		add(labelNomeCliente, constraints);
		constraints.gridx = 3;
		jTNomeCliente.setPreferredSize(defaultDimension);
		add(jTNomeCliente, constraints);

		constraints.gridx = 4;
		labelSobrenomeCliente.setPreferredSize(defaultDimension);
		add(labelSobrenomeCliente, constraints);
		constraints.gridx = 5;
		jTSobrenomeCliente.setPreferredSize(defaultDimension);
		add(jTSobrenomeCliente, constraints);

		constraints.gridy = 2;
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

		constraints.gridy = 4;
		constraints.gridx = 0;
		labelDias.setPreferredSize(defaultDimension);
		add(labelDias, constraints);
		constraints.gridx = 1;
		jTDias.setPreferredSize(defaultDimension);
		add(jTDias, constraints);

		constraints.gridx = 2;
		labelDataLocacao.setPreferredSize(defaultDimension);
		add(labelDataLocacao, constraints);
		constraints.gridx = 3;
		jTDataLocacao.setPreferredSize(defaultDimension);
		add(jTDataLocacao, constraints);

		constraints.gridy = 5;
		constraints.gridx = 0;
		constraints.gridwidth = GridBagConstraints.REMAINDER;
		btnLocarVeiculo.setPreferredSize(new Dimension(120, 20));
		add(btnLocarVeiculo, constraints);

		constraints.anchor = GridBagConstraints.CENTER;
		
		JScrollPane scrollPaneClientes = new JScrollPane();
		scrollPaneClientes.setViewportView(tabelaCliente);

		constraints.gridy = 1;
		scrollPaneClientes.setPreferredSize(new Dimension(500, 70));
		add(scrollPaneClientes, constraints);

		JScrollPane scrollPaneVeiculos = new JScrollPane();
		scrollPaneVeiculos.setViewportView(tabelaVeiculosDisponiveis);

		constraints.gridy = 3;
		scrollPaneVeiculos.setPreferredSize(new Dimension(500, 120));
		add(scrollPaneVeiculos, constraints);

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
	}

	private void initializeElements() {

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
