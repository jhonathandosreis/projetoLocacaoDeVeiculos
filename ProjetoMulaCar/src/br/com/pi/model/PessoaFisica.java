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

/**
 *
 * @author jhonlinux
 */

public class PessoaFisica extends Clientes {

    //--- ATRIBUTOS ----------------------------------------------------------------------------------->
    //
    private int iden = 0;
    private int rg = 0;
    private double cpf = 0;
    private Clientes cliente = null;
    //--- FIM ATRIBUTOS -------------------------------------------------------------------------------|
    //

    //--- CONSTRUTORES -------------------------------------------------------------------------------->
    //
    public PessoaFisica() {
        
    }

    public PessoaFisica(int iden, String nome, double telefone, String email,Enderecos enderecos, Clientes cliente, int rg, double cpf) {
        super(iden, nome, telefone, email, enderecos);
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

    public void setPfi_cli_iden(Clientes pfi_cli_iden) {
        this.cliente = pfi_cli_iden;
    }
    //--- FIM SET -------------------------------------------------------------------------------------|
    //
}
