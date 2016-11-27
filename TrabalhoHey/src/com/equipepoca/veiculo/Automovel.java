/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.equipepoca.veiculo;

import com.equipepoca.locacao.Locacao;

/**
 *
 * @author amanda.naito
 */
public class Automovel extends Veiculo {
    ModeloAutomovel modelo;
    
    public Automovel(Marca marca, Estado estado, Locacao locacao, Categoria categoria, double valorDeCompra, String placa, int ano, ModeloAutomovel modelo){
        super(marca, estado, locacao, categoria, valorDeCompra, placa, ano);
        this.modelo = modelo;
    }
    
    @Override
    public double getValorDiariaLocacao(){
        switch (getCategoria()){
            case POPULAR:
                return 100.0;
            case INTERMEDIARIO:
                return 300.0;
            case LUXO:
                return 450.0;
            default:
                return 0.0;
        }
    }
    
    public ModeloAutomovel getModelo(){
        return modelo;
    }
}
