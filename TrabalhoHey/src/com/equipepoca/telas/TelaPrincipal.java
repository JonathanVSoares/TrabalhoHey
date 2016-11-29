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
		
		setSize(700, 600);
		
		add(painelTopo, BorderLayout.NORTH);
		add(telaAtual, BorderLayout.CENTER);
		
		btnTelaManterCliente.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				remove(telaAtual);
				telaAtual = new TelaManterClientes();
				add(telaAtual, BorderLayout.CENTER);
				revalidate();
			}
		});
		
		btnTelaIncluirVeiculo.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				remove(telaAtual);
				telaAtual = new TelaIncluirVeiculo();
				add(telaAtual, BorderLayout.CENTER);
				revalidate();
			}
		});
		
		btnTelaLocarVeiculo.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				remove(telaAtual);
				telaAtual = new TelaLocarVeiculo();
				add(telaAtual, BorderLayout.CENTER);
				revalidate();
			}
		});
		
		btnTelaDevolverVeiculo.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				remove(telaAtual);
				telaAtual = new TelaDevolverVeiculo();
				add(telaAtual, BorderLayout.CENTER);
				revalidate();
			}
		});
		
		btnTelaVenderVeiculo.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				remove(telaAtual);
				telaAtual = new TelaVenderVeiculo();
				add(telaAtual, BorderLayout.CENTER);
				revalidate();
			}
		});
	}

	public static void main(String[] args) {
		new TelaPrincipal().setVisible(true);
	}
}
