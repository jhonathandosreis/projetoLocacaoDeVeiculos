/*
 *  -------------------------------------------------------------------------------------------------->
 *  Licença    : MIT - Copyright 2019 Jhonathan, Gustavo e Miguel 
 *  Criado em  : 21/11/2020 03:31:30 
 *  Instituição: FACULDADE SENAI FATESG
 *  Curso      : Análise e Desenvolvimento de sistemas - Módulo 3 - 2020/11
 *  Disciplina : Projeto Integrador
 *  Alunos     : Jhonathan dos Reis, Gustavo Gabriel e Miguel Neto
 *  Projeto    : Projeto Locação de Veículos
 *  Exercício  : Mula Car
 *  ---------------------------------------------------------------------------------------------------
 *  Propósito do arquivo: 
 *  ---------------------------------------------------------------------------------------------------| 
 */

package br.com.pi.model;

import java.util.ArrayList;

/**
 *
 * @author jhonlinux
 */

public class PessoasFisicas extends Clientes {

    //--- ATRIBUTOS ----------------------------------------------------------------------------------->
    //
    private int iden = 0;
    private int rg = 0;
    private double cpf = 0;
    private Clientes cliente = null;
    private ArrayList<Fotos> fotos = new ArrayList<>();
    //--- FIM ATRIBUTOS -------------------------------------------------------------------------------|
    //

    //--- CONSTRUTORES -------------------------------------------------------------------------------->
    //
    public PessoasFisicas() {
        
    }

    public PessoasFisicas(int iden, String nome, double telefone, String email,
            Enderecos enderecos,String status, float multa, Clientes cliente, int rg, double cpf) {
        super(iden, nome, telefone, email, enderecos, status, multa);
        this.iden = iden;
        this.rg = rg;
        this.cpf = cpf;
        this.cliente = cliente;
    }
    //--- FIM CONSTRUTORES ----------------------------------------------------------------------------|
    //

    //--- GET ----------------------------------------------------------------------------------------->
    //
    public int getIden() {
        return iden;
    }

    public ArrayList<Fotos> getFotos() {
        return fotos;
    }
    
    public int getRg() {
        return rg;
    }

    public double getCpf() {
        return cpf;
    }

    public Clientes getCliente() {
        return cliente;
    }
    //--- FIM GET -------------------------------------------------------------------------------------|
    //

    //--- SET ----------------------------------------------------------------------------------------->
    //
    public void setIden(int iden) {
        this.iden = iden;
    }

    public void setRg(int rg) {
        this.rg = rg;
    }

    public void setCpf(double cpf) {
        this.cpf = cpf;
    }
    
    public void setFotos(ArrayList<Fotos> fotos) {
        this.fotos = fotos;
    }

    public void setCliente(Clientes cliente) {
        this.cliente = cliente;
    }
    
    //--- FIM SET -------------------------------------------------------------------------------------|
    //
}
