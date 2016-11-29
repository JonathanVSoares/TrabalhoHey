package com.equipepoca.telas;

import com.equipepoca.veiculo.Automovel;
import com.equipepoca.veiculo.Categoria;
import com.equipepoca.veiculo.Estado;
import com.equipepoca.veiculo.Marca;
import com.equipepoca.veiculo.ModeloAutomovel;
import com.equipepoca.veiculo.ModeloMotocicleta;
import com.equipepoca.veiculo.ModeloVan;
import com.equipepoca.veiculo.Motocicleta;
import com.equipepoca.veiculo.TipoVeiculo;
import com.equipepoca.veiculo.Van;
import com.equipepoca.veiculo.Veiculo;
import com.equipepoca.veiculo.VeiculoDAO;
import com.equipepoca.veiculo.VeiculoDAOImpl;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;
import java.text.ParseException;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.text.MaskFormatter;

/**
 *
 * @author amanda.naito
 */
public class TelaIncluirVeiculo extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4213880766225320156L;

	private final JLabel labelTipoVeiculo;
	private final JLabel labelMarca;
	private final JLabel labelEstado;
	private final JLabel labelCategoria;
	private final JLabel labelModelo;
	private final JLabel labelValorDeCompra;
	private final JLabel labelAno;
	private final JLabel labelPlaca;

	private final JComboBox<TipoVeiculo> tipoVeiculo;
	private final JComboBox<Marca> marca;
	private final JComboBox<Estado> estado;
	private final JComboBox<Categoria> categoria;
	private final JComboBox<String> modelo;
	private final JFormattedTextField valorDeCompra;
	private final JFormattedTextField placa;
	private final JFormattedTextField ano;
	private final JButton btnIncluirVeiculo;

	DefaultComboBoxModel<String> model;

	public TelaIncluirVeiculo() {
		labelTipoVeiculo = new JLabel("Tipo do Veiculo:");
		labelMarca = new JLabel("Marca:");
		labelEstado = new JLabel("Estado:");
		labelCategoria = new JLabel("Categoria:");
		labelModelo = new JLabel("Modelo:");
		labelValorDeCompra = new JLabel("Valor de Compra:");
		labelPlaca = new JLabel("Placa:");
		labelAno = new JLabel("Ano:");

		NumberFormat format = NumberFormat.getCurrencyInstance();

		tipoVeiculo = new JComboBox<TipoVeiculo>(TipoVeiculo.values());
		marca = new JComboBox<Marca>(Marca.values());
		estado = new JComboBox<Estado>(Estado.values());
		categoria = new JComboBox<Categoria>(Categoria.values());
		modelo = new JComboBox<String>();
		valorDeCompra = new JFormattedTextField(format);
		placa = new JFormattedTextField();
		ano = new JFormattedTextField();
		btnIncluirVeiculo = new JButton("Incluir Veiculo");

		valorDeCompra.setValue(new Double(0.0));

		try {
			MaskFormatter placaMask = new MaskFormatter("UUU-####");
			placaMask.setPlaceholderCharacter('_');
			placaMask.install(placa);

			MaskFormatter anoMask = new MaskFormatter("####");
			anoMask.setPlaceholderCharacter('_');
			anoMask.install(ano);
		} catch (ParseException ex) {
		}

		setSize(300, 400);
		setLocation(600, 200);

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
		labelAno.setBounds(10, 220, 110, 20);
		ano.setBounds(120, 220, 140, 20);
		add(labelAno);
		add(ano);
		btnIncluirVeiculo.setBounds(10, 250, 140, 20);
		add(btnIncluirVeiculo);

		tipoVeiculo.setSelectedIndex(-1);
		marca.setSelectedIndex(-1);
		estado.setSelectedIndex(-1);
		categoria.setSelectedIndex(-1);
		modelo.setSelectedIndex(-1);

		model = new DefaultComboBoxModel<String>();
		modelo.setModel(model);

		updateModelos();

		tipoVeiculo.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				updateModelos();
			}
		});

		btnIncluirVeiculo.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				TipoVeiculo tipoVeiculoSelecionado = tipoVeiculo.getItemAt(tipoVeiculo.getSelectedIndex());
				Marca marcaSelecionada = marca.getItemAt(marca.getSelectedIndex());
				Estado estadoSelecionado = estado.getItemAt(estado.getSelectedIndex());
				Categoria categoriaSelecionada = categoria.getItemAt(categoria.getSelectedIndex());
				String modeloSelecionado = modelo.getItemAt(modelo.getSelectedIndex());

				String sValorDeCompra = valorDeCompra.getText();
				double valorDeCompraEscolhido = 0;
				try {
					valorDeCompraEscolhido = NumberFormat.getCurrencyInstance().parse(sValorDeCompra).doubleValue();
				} catch (ParseException e1) {
					e1.printStackTrace();
				}
				String placaEscolhida = placa.getText().replace("-", "");
				int anoEscolhido = Integer.valueOf(ano.getText());

				Veiculo veiculo;

				switch (tipoVeiculoSelecionado) {
				case AUTOMOVEL:
					veiculo = new Automovel(marcaSelecionada, estadoSelecionado, null, categoriaSelecionada,
							valorDeCompraEscolhido, placaEscolhida, anoEscolhido,
							ModeloAutomovel.valueOf(modeloSelecionado));
					break;
				case MOTOCICLETA:
					veiculo = new Motocicleta(marcaSelecionada, estadoSelecionado, null, categoriaSelecionada,
							valorDeCompraEscolhido, placaEscolhida, anoEscolhido,
							ModeloMotocicleta.valueOf(modeloSelecionado));
					break;
				case VAN:
					veiculo = new Van(marcaSelecionada, estadoSelecionado, null, categoriaSelecionada,
							valorDeCompraEscolhido, placaEscolhida, anoEscolhido, ModeloVan.valueOf(modeloSelecionado));
					break;
				default:
					return;
				}

				VeiculoDAO dao = new VeiculoDAOImpl();
				dao.incluir(veiculo);
			}
		});
	}

	private void updateModelos() {
		if (tipoVeiculo.getSelectedIndex() == -1)
			return;

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
		modelo.setSelectedIndex(-1);
	}

	public static void main(String[] args) {
		new TelaIncluirVeiculo().setVisible(true);
	}
}
