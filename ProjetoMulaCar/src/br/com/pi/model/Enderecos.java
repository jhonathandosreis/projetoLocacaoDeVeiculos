/*
 *  -------------------------------------------------------------------------------------------------->
 *  Licença    : MIT - Copyright 2019 Jhonathan, Gustavo e Miguel 
 *  Criado em  : 21/11/2020 03:58:41 
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
public class Enderecos {

    //--- ATRIBUTOS ----------------------------------------------------------------------------------->
    //
    private int iden = 0;
    private String rua = "";
    private float numero = 0;
    private String logradouro = "";
    private int cep = 0;
    private String complemento = "";
    //--- FIM ATRIBUTOS -------------------------------------------------------------------------------|
    //

    //--- CONSTRUTORES -------------------------------------------------------------------------------->
    //
    public Enderecos() {
    
    }
    
    public Enderecos(int iden, String rua, float numero, 
            String logradouro, int cep, String complemento) {
        this.iden = iden;
        this.rua = rua;
        this.numero = numero;
        this.logradouro = logradouro;
        this.cep = cep;
        this.complemento = complemento;
    }
    //--- FIM CONSTRUTORES ----------------------------------------------------------------------------|
    //

    //--- GET ----------------------------------------------------------------------------------------->
    //
    public int getIden() {
        return iden;
    }

    public String getRua() {
        return rua;
    }

    public float getNumero() {
        return numero;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public int getCep() {
        return cep;
    }

    public String getComplemento() {
        return complemento;
    }
    //--- FIM GET -------------------------------------------------------------------------------------|
    //

    //--- SET ----------------------------------------------------------------------------------------->
    //

    //--- FIM SET -------------------------------------------------------------------------------------|
    //

    //--- CREATE -------------------------------------------------------------------------------------->
    //

    //--- FIM CREATE ----------------------------------------------------------------------------------|
    //

    //--- DELETE -------------------------------------------------------------------------------------->
    //
    
    //--- FIM DELETE ----------------------------------------------------------------------------------|
    //
    
    //--- UPDATE -------------------------------------------------------------------------------------->
    //

    //--- FIM UPDATE ----------------------------------------------------------------------------------|
    //
    
    //--- READ ---------------------------------------------------------------------------------------->
    //

    //--- FIM READ ------------------------------------------------------------------------------------|
    //



}
