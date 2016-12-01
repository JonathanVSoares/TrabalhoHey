package com.equipepoca.telas;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class TelaPrincipal extends JFrame {
	private static final long serialVersionUID = -2244840086438448336L;

	private final JButton btnTelaManterCliente;
	private final JButton btnTelaIncluirVeiculo;
	private final JButton btnTelaLocarVeiculo;
	private final JButton btnTelaDevolverVeiculo;
	private final JButton btnTelaVenderVeiculo;

	private JPanel telaAtual;

	public TelaPrincipal() {
		btnTelaManterCliente = new JButton("Manter Cliente");
		btnTelaIncluirVeiculo = new JButton("Incluir Veiculo");
		btnTelaLocarVeiculo = new JButton("Locar Veiculo");
		btnTelaDevolverVeiculo = new JButton("Devolver Veiculo");
		btnTelaVenderVeiculo = new JButton("Vender Veiculo");

		JPanel painelTopo = new JPanel(new FlowLayout());

		painelTopo.add(btnTelaManterCliente);
		painelTopo.add(btnTelaIncluirVeiculo);
		painelTopo.add(btnTelaLocarVeiculo);
		painelTopo.add(btnTelaDevolverVeiculo);
		painelTopo.add(btnTelaVenderVeiculo);

		telaAtual = new TelaManterClientes();
		
		setLocation(200, 200);
		setSize(900, 500);
		
		add(painelTopo, BorderLayout.NORTH);
		add(telaAtual, BorderLayout.CENTER);
		setTitle("Manter Cliente");
		
		btnTelaManterCliente.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				remove(telaAtual);
				telaAtual = new TelaManterClientes();
				add(telaAtual, BorderLayout.CENTER);
				revalidate();
				setTitle("Manter Cliente");
			}
		});
		
		btnTelaIncluirVeiculo.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				remove(telaAtual);
				telaAtual = new TelaIncluirVeiculo();
				add(telaAtual, BorderLayout.CENTER);
				revalidate();
				setTitle("Incluir Veiculo");
			}
		});
		
		btnTelaLocarVeiculo.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				remove(telaAtual);
				telaAtual = new TelaLocarVeiculo();
				add(telaAtual, BorderLayout.CENTER);
				revalidate();
				setTitle("Locar Veiculo");
			}
		});
		
		btnTelaDevolverVeiculo.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				remove(telaAtual);
				telaAtual = new TelaDevolverVeiculo();
				add(telaAtual, BorderLayout.CENTER);
				revalidate();
				setTitle("Devolver Veiculo");
			}
		});
		
		btnTelaVenderVeiculo.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				remove(telaAtual);
				telaAtual = new TelaVenderVeiculo();
				add(telaAtual, BorderLayout.CENTER);
				revalidate();
				setTitle("Vender Veiculo");
			}
		});
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	public static void main(String[] args) {
		new TelaPrincipal().setVisible(true);
	}
}
