/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.equipepoca.locacao;

import com.equipepoca.cliente.Cliente;

import java.util.Calendar;

/**
 *
 * @author amanda.naito
 */
public class Locacao {
	private int id;
    private int dias;
    private double valor;
    private Calendar data;
    private Cliente cliente;

    public Locacao(int id, int dias, double valor, Calendar data, Cliente cliente) {
    	this.id = id;
        this.dias = dias;
        this.valor = valor;
        this.data = data;
        this.cliente = cliente;
    }

    public Locacao(int dias, double valor, Calendar data, Cliente cliente) {
        this.dias = dias;
        this.valor = valor;
        this.data = data;
        this.cliente = cliente;
    }

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

    public int getDias() {
        return dias;
    }

    public double getValor() {
        return valor;
    }

    public Calendar getData() {
        return data;
    }

    public Cliente getCliente() {
        return cliente;
    }
}
