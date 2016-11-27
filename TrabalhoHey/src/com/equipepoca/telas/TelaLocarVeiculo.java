package com.equipepoca.telas;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import com.equipepoca.veiculo.Categoria;
import com.equipepoca.veiculo.Marca;
import com.equipepoca.veiculo.TipoVeiculo;

public class TelaLocarVeiculo extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7485148122304664786L;

	private final JLabel labelNomeCliente;
	private final JLabel labelSobrenomeCliente;
	private final JLabel labelCPFCliente;

	private final JLabel labelTipoVeiculo;
	private final JLabel labelMarca;
	private final JLabel labelCategoria;

	private final JLabel labelDataLocacao;

	private final JTextField jTNomeCliente;
	private final JTextField jTSobrenomeCliente;
	private final JTextField jTCPFCliente;

	private final JComboBox<TipoVeiculo> jCBTipoVeiculo;
	private final JComboBox<Marca> jCBMarca;
	private final JComboBox<Categoria> jCBCategoria;

	private final JFormattedTextField jTDataLocacao;

	public TelaLocarVeiculo() {
		super("Locar Veiculo");

		labelNomeCliente = new JLabel("Nome:");
		labelSobrenomeCliente = new JLabel("Sobrenome:");
		labelCPFCliente = new JLabel("CPF:");

		labelTipoVeiculo = new JLabel("Tipo do Veiculo:");
		labelMarca = new JLabel("Marca:");
		labelCategoria = new JLabel("Categoria:");

		labelDataLocacao = new JLabel("Data de locação:");

		DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");

		jTNomeCliente = new JTextField();
		jTSobrenomeCliente = new JTextField();
		jTCPFCliente = new JTextField();

		jCBTipoVeiculo = new JComboBox<TipoVeiculo>(TipoVeiculo.values());
		jCBMarca = new JComboBox<Marca>(Marca.values());
		jCBCategoria = new JComboBox<Categoria>(Categoria.values());

		jTDataLocacao = new JFormattedTextField(dateFormat);

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

		labelTipoVeiculo.setBounds(10, 100, 110, 20);
		jCBTipoVeiculo.setBounds(120, 100, 140, 20);
		add(labelTipoVeiculo);
		add(jCBTipoVeiculo);

		labelMarca.setBounds(10, 130, 110, 20);
		jCBMarca.setBounds(120, 130, 140, 20);
		add(labelMarca);
		add(jCBMarca);

		labelCategoria.setBounds(10, 160, 110, 20);
		jCBCategoria.setBounds(120, 160, 140, 20);
		add(labelCategoria);
		add(jCBCategoria);

		labelDataLocacao.setBounds(10, 190, 110, 20);
		jTDataLocacao.setBounds(120, 190, 140, 20);
		add(labelDataLocacao);
		add(jTDataLocacao);

		jCBTipoVeiculo.setSelectedIndex(-1);
		jCBMarca.setSelectedIndex(-1);
		jCBCategoria.setSelectedIndex(-1);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
