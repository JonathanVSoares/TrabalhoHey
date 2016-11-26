/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.equipepoca.telas;

import com.equipepoca.veiculo.Categoria;
import com.equipepoca.veiculo.Estado;
import com.equipepoca.veiculo.Marca;
import com.equipepoca.veiculo.ModeloAutomovel;
import com.equipepoca.veiculo.ModeloMotocicleta;
import com.equipepoca.veiculo.ModeloVan;
import com.equipepoca.veiculo.TipoVeiculo;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;
import java.text.ParseException;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.text.MaskFormatter;

/**
 *
 * @author amanda.naito
 */
public class TelaIncluirVeiculo extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4213880766225320156L;

	private final JComboBox<TipoVeiculo> tipoVeiculo;
	private final JComboBox<Marca> marca;
	private final JComboBox<Estado> estado;
	private final JComboBox<Categoria> categoria;
	private final JComboBox<String> modelo;
	private final JFormattedTextField valorDeCompra;
	private final JFormattedTextField placa;
	private final JButton incluirVeiculo;

	private final JLabel labelTipoVeiculo;
	private final JLabel labelMarca;
	private final JLabel labelEstado;
	private final JLabel labelCategoria;
	private final JLabel labelModelo;
	private final JLabel labelValorDeCompra;
	private final JLabel labelPlaca;

	private final Container contentPane;

	DefaultComboBoxModel<String> model;

	public TelaIncluirVeiculo() {
		super("Incluir Veiculo");

		NumberFormat format = NumberFormat.getCurrencyInstance();
		
		tipoVeiculo = new JComboBox<TipoVeiculo>(TipoVeiculo.values());
		marca = new JComboBox<Marca>(Marca.values());
		estado = new JComboBox<Estado>(Estado.values());
		categoria = new JComboBox<Categoria>(Categoria.values());
		modelo = new JComboBox<String>();
		valorDeCompra = new JFormattedTextField(format);
		placa = new JFormattedTextField();
		incluirVeiculo = new JButton("Incluir Veiculo");
		
		valorDeCompra.setValue(new Double(0.0));
		
		try {
	        MaskFormatter placaMask = new MaskFormatter("UUU-####");
	        placaMask.setPlaceholderCharacter('_');
	        placaMask.install(placa);
	    } catch (ParseException ex) {
	    }
		
		labelTipoVeiculo = new JLabel("Tipo Do Veiculo:");
		labelMarca = new JLabel("Marca:");
		labelEstado = new JLabel("Estado:");
		labelCategoria = new JLabel("Categoria:");
		labelModelo = new JLabel("Modelo:");
		labelValorDeCompra = new JLabel("Valor de Compra:");
		labelPlaca = new JLabel("Placa:");

		contentPane = getContentPane();
		contentPane.setLayout(null);

		labelTipoVeiculo.setBounds(10, 10, 110, 20);
		tipoVeiculo.setBounds(120, 10, 140, 20);
		add(labelTipoVeiculo);
		add(tipoVeiculo);
		labelMarca.setBounds(10, 40, 110, 20);
		marca.setBounds(120, 40, 140, 20);
		add(labelMarca);
		add(marca);
		labelEstado.setBounds(10, 70, 110, 20);
		estado.setBounds(120, 70, 140, 20);
		add(labelEstado);
		add(estado);
		labelCategoria.setBounds(10, 100, 110, 20);
		categoria.setBounds(120, 100, 140, 20);
		add(labelCategoria);
		add(categoria);
		labelModelo.setBounds(10, 130, 110, 20);
		modelo.setBounds(120, 130, 140, 20);
		add(labelModelo);
		add(modelo);
		labelValorDeCompra.setBounds(10, 160, 110, 20);
		valorDeCompra.setBounds(120, 160, 140, 20);
		add(labelValorDeCompra);
		add(valorDeCompra);
		labelPlaca.setBounds(10, 190, 110, 20);
		placa.setBounds(120, 190, 140, 20);
		add(labelPlaca);
		add(placa);
		incluirVeiculo.setBounds(10, 220, 140, 20);
		add(incluirVeiculo);

		model = new DefaultComboBoxModel<String>();
		modelo.setModel(model);

		updateModelos();
		
		tipoVeiculo.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				updateModelos();
			}
		});

		incluirVeiculo.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// ToDo: exceptions, criar instancia/JDBC
			}
		});
		
		setSize(300, 300);
		setLocation(600, 200);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	private void updateModelos() {
		model.removeAllElements();
		switch ((TipoVeiculo) tipoVeiculo.getSelectedItem()) {
		case AUTOMOVEL:
			for (ModeloAutomovel modeloAutomovel : ModeloAutomovel.values())
				model.addElement(modeloAutomovel.name());
			break;
		case MOTOCICLETA:
			for (ModeloMotocicleta modeloMotocicleta : ModeloMotocicleta.values())
				model.addElement(modeloMotocicleta.name());
			break;
		case VAN:
			for (ModeloVan modeloVan : ModeloVan.values())
				model.addElement(modeloVan.name());
			break;
		default:
			break;
		}
	}

	public static void main(String[] args) {
		new TelaIncluirVeiculo().setVisible(true);
	}
}
