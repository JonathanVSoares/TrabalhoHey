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
public class Motocicleta extends Veiculo{
    ModeloMotocicleta modelo;
    
    public Motocicleta(Marca marca, Estado estado, Locacao locacao, Categoria categoria, double valorDeCompra, String placa, int ano){
        super(marca, estado, locacao, categoria, valorDeCompra, placa, ano);
    }
    
    @Override
    public double getValorDiariaLocacao(){
        switch (getCategoria()){
            case POPULAR:
                return 70.0;
            case INTERMEDIARIO:
                return 200.0;
            case LUXO:
                return 350.0;
            default:
                return 0.0;
        }
    }
    
    public ModeloMotocicleta getModelo(){
        return modelo;
    }
}
