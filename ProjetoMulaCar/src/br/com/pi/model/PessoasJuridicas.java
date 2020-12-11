/*
 *  -------------------------------------------------------------------------------------------------->
 *  Licença    : MIT - Copyright 2019 Jhonathan, Gustavo e Miguel 
 *  Criado em  : 21/11/2020 03:48:54 
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
 * @author GUSTAVO
 */
public class PessoasJuridicas{

    //--- ATRIBUTOS ----------------------------------------------------------------------------------->
    //
    private int iden = 0;
    private String cnpj ="";
    private String nomeFantasia = "";
    private String razaoSocial = "";
    private Clientes cliente = null;
    //--- FIM ATRIBUTOS -------------------------------------------------------------------------------|
    //

    //--- CONSTRUTORES -------------------------------------------------------------------------------->
    //
    public PessoasJuridicas() {
    }

    public PessoasJuridicas(int iden, String cnpj, String nomeFantasia, String razaoSocial, Clientes cliente) {   
        this.iden = iden;
        this.cnpj = cnpj;
        this.nomeFantasia = nomeFantasia;
        this.razaoSocial = razaoSocial;
        this.cliente = cliente;
    }

    //--- FIM CONSTRUTORES ----------------------------------------------------------------------------|
    //
    //--- GET ----------------------------------------------------------------------------------------->
    //
    public int getIden() {
        return iden;
    }

    public String getCnpj() {
        return cnpj;
    }

    public String getNomeFantasia() {
        return nomeFantasia;
    }

    public String getRazaoSocial() {
        return razaoSocial;
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

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj.trim();
    }

    public void setNomeFantasia(String nomeFantasia) {
        this.nomeFantasia = nomeFantasia.trim().toUpperCase();
    }

    public void setRazaoSocial(String razaoSocial) {
        this.razaoSocial = razaoSocial.trim().toUpperCase();
    }

    public void setCliente(Clientes cliente) {
        this.cliente = cliente;
    }
    //--- FIM SET -------------------------------------------------------------------------------------|
    //
}
