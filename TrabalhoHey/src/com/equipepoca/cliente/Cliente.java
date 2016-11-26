/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.equipepoca.cliente;

/**
 *
 * @author amanda.naito
 */
public class Cliente {
    private int id;
    private String nome;
    private String sobreNome;
    private String rg;
    private String cpf;
    private String endereco;

    public Cliente() {
    }
    
    public Cliente(int id, String nome, String sobreNome, String rg, String cpf, String endereco) {
        this.id = id;
        this.nome = nome;
        this.sobreNome = sobreNome;
        this.rg = rg;
        this.cpf = cpf;
        this.endereco = endereco;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSobreNome() {
        return sobreNome;
    }

    public void setSobreNome(String sobreNome) {
        this.sobreNome = sobreNome;
    }

    public String getRg() {
        return rg;
    }

    public void setRg(String rg) {
        this.rg = rg;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }
}
