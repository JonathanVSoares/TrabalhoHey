package com.equipepoca.telas;

import com.equipepoca.exception.CampoPreenchidoIncorretamente;
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

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;
import java.text.ParseException;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
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
		super(new GridBagLayout());

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
		} catch (ParseException e) {
			JOptionPane.showMessageDialog(getParent(), e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
		}

		Dimension defaultDimension = new Dimension(100, 20);

		labelTipoVeiculo.setPreferredSize(defaultDimension);
		tipoVeiculo.setPreferredSize(defaultDimension);
		labelMarca.setPreferredSize(defaultDimension);
		marca.setPreferredSize(defaultDimension);
		labelEstado.setPreferredSize(defaultDimension);
		estado.setPreferredSize(defaultDimension);
		labelCategoria.setPreferredSize(defaultDimension);
		categoria.setPreferredSize(defaultDimension);
		labelModelo.setPreferredSize(defaultDimension);
		modelo.setPreferredSize(defaultDimension);
		labelValorDeCompra.setPreferredSize(defaultDimension);
		valorDeCompra.setPreferredSize(defaultDimension);
		labelPlaca.setPreferredSize(defaultDimension);
		placa.setPreferredSize(defaultDimension);
		labelAno.setPreferredSize(defaultDimension);
		ano.setPreferredSize(defaultDimension);

		btnIncluirVeiculo.setPreferredSize(new Dimension(120, 20));

		GridBagConstraints constraints = new GridBagConstraints();

		constraints.anchor = GridBagConstraints.LINE_START;
		constraints.insets = new Insets(10, 10, 10, 10);

		constraints.gridy = 0;
		constraints.gridx = 0;
		add(labelTipoVeiculo, constraints);
		constraints.gridx = 1;
		add(tipoVeiculo, constraints);

		constraints.gridx = 2;
		add(labelMarca, constraints);
		constraints.gridx = 3;
		add(marca, constraints);

		constraints.gridy = 1;
		constraints.gridx = 0;
		add(labelEstado, constraints);
		constraints.gridx = 1;
		add(estado, constraints);

		constraints.gridx = 2;
		add(labelCategoria, constraints);
		constraints.gridx = 3;
		add(categoria, constraints);

		constraints.gridy = 2;
		constraints.gridx = 0;
		add(labelModelo, constraints);
		constraints.gridx = 1;
		add(modelo, constraints);

		constraints.gridx = 2;
		add(labelValorDeCompra, constraints);
		constraints.gridx = 3;
		add(valorDeCompra, constraints);

		constraints.gridy = 3;
		constraints.gridx = 0;
		add(labelPlaca, constraints);
		constraints.gridx = 1;
		add(placa, constraints);

		constraints.gridx = 2;
		add(labelAno, constraints);
		constraints.gridx = 3;
		add(ano, constraints);

		constraints.gridy = 4;
		constraints.gridx = 2;
		constraints.gridwidth = 2;
		constraints.anchor = GridBagConstraints.LAST_LINE_END;
		add(btnIncluirVeiculo, constraints);

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
			public void actionPerformed(ActionEvent event) {
				double valorDeCompraEscolhido = 0;
				try {
					checkFields();

					String sValorDeCompra = valorDeCompra.getText();
					valorDeCompraEscolhido = NumberFormat.getCurrencyInstance().parse(sValorDeCompra).doubleValue();
				} catch (CampoPreenchidoIncorretamente e) {
					JOptionPane.showMessageDialog(getParent(), e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
					return;
				} catch (ParseException e) {
					JOptionPane.showMessageDialog(getParent(),
							"Houve algum problema com o valor de compra. Por favor, redigite-o", "Erro",
							JOptionPane.ERROR_MESSAGE);
					return;
				}

				TipoVeiculo tipoVeiculoSelecionado = tipoVeiculo.getItemAt(tipoVeiculo.getSelectedIndex());
				Marca marcaSelecionada = marca.getItemAt(marca.getSelectedIndex());
				Estado estadoSelecionado = estado.getItemAt(estado.getSelectedIndex());
				Categoria categoriaSelecionada = categoria.getItemAt(categoria.getSelectedIndex());
				String modeloSelecionado = modelo.getItemAt(modelo.getSelectedIndex());
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

				JOptionPane.showMessageDialog(getParent(), "Veiculo Cadastrado com Sucesso!", "Veiculo Cadastrado",
						JOptionPane.INFORMATION_MESSAGE);

				clearFields();
			}

			private void checkFields() throws CampoPreenchidoIncorretamente, ParseException {
				if (tipoVeiculo.getSelectedIndex() < 0 || marca.getSelectedIndex() < 0 || estado.getSelectedIndex() < 0
						|| categoria.getSelectedIndex() < 0 || modelo.getSelectedIndex() < 0
						|| placa.getText().replace("-", "").replace("_", "").length() != 7
						|| ano.getText().replace("_", "").length() != 4
						|| NumberFormat.getCurrencyInstance().parse(valorDeCompra.getText()).doubleValue() == 0)
					throw new CampoPreenchidoIncorretamente();
			}
		});
	}

	private void clearFields() {
		tipoVeiculo.setSelectedIndex(-1);
		marca.setSelectedIndex(-1);
		estado.setSelectedIndex(-1);
		categoria.setSelectedIndex(-1);
		modelo.setSelectedIndex(-1);
		valorDeCompra.setValue(0);
		placa.setValue("");
		ano.setValue("");

		try {
			MaskFormatter placaMask = new MaskFormatter("UUU-####");
			placaMask.setPlaceholderCharacter('_');
			placaMask.install(placa);

			MaskFormatter anoMask = new MaskFormatter("####");
			anoMask.setPlaceholderCharacter('_');
			anoMask.install(ano);
		} catch (ParseException e) {
			JOptionPane.showMessageDialog(getParent(), e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
		}
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
}
