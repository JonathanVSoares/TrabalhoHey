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
public class Van extends Veiculo{
    ModeloVan modelo;
    
    public Van(Marca marca, Estado estado, Locacao locacao, Categoria categoria, double valorDeCompra, String placa, int ano){
        super(marca, estado, locacao, categoria, valorDeCompra, placa, ano);
    }
    
    @Override
    public double getValorDiariaLocacao(){
        switch (getCategoria()){
            case POPULAR:
                return 200.0;
            case INTERMEDIARIO:
                return 400.0;
            case LUXO:
                return 600.0;
            default:
                return 0.0;
        }
    }
    
    public ModeloVan getModelo(){
        return modelo;
    }
}
