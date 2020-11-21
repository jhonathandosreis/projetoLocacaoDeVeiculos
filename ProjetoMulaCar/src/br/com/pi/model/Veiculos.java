/*
 *  -------------------------------------------------------------------------------------------------->
 *  Licença    : MIT - Copyright 2019 Jhonathan, Gustavo e Miguel 
 *  Criado em  : 21/11/2020 04:22:13 
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
public class Veiculos {

    //--- ATRIBUTOS ----------------------------------------------------------------------------------->
    //
    private int iden = 0;
    private String placa = "";
    private int quilimetragem = 0;
    private int anoDoVeiculo = 0;
    private double renavam = 0;
    private String status = "";
    private int precoDeCompra = 0;
    private int observacoes = 0;
    private float capacidade = 0;
    //--- FIM ATRIBUTOS -------------------------------------------------------------------------------|
    //

    //--- CONSTRUTORES -------------------------------------------------------------------------------->
    //
    public Veiculos() {
        
    }
    
    public Veiculos(int iden, String placa, int quilometragem, int anoDoVeiculo, 
            double renavam, String status, int precoDeCompra, int observacoes, float capacidade) {
        this.iden = iden;
        this.placa = placa;
        this.quilimetragem = quilometragem;
        this.anoDoVeiculo = anoDoVeiculo;
        this.renavam = renavam;
        this.status = status;
        this.precoDeCompra = precoDeCompra;
        this.observacoes = observacoes;
        this.capacidade = capacidade;
    }
    //--- FIM CONSTRUTORES ----------------------------------------------------------------------------|
    //

    //--- GET ----------------------------------------------------------------------------------------->
    //
    public int getIden() {
        return iden;
    }

    public String getPlaca() {
        return placa;
    }

    public int getQuilimetragem() {
        return quilimetragem;
    }

    public int getAnoDoVeiculo() {
        return anoDoVeiculo;
    }

    public double getRenavam() {
        return renavam;
    }

    public String getStatus() {
        return status;
    }

    public int getPrecoDeCompra() {
        return precoDeCompra;
    }

    public int getObservacoes() {
        return observacoes;
    }

    public float getCapacidade() {
        return capacidade;
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
