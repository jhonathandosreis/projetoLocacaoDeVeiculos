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
    private double renavam = 0;
    private String status = "";
    private int precoDeCompra = 0;
    private float anoFabricacao = 0;
    private String observacoes = "";
    private float capacidade = 0;
    private Modelos modelo = null;
    private TiposDeVeiculos tiposDeVeiculo = null;
    //--- FIM ATRIBUTOS -------------------------------------------------------------------------------|
    //

    //--- CONSTRUTORES -------------------------------------------------------------------------------->
    //
    public Veiculos() {
        
    }
    
    public Veiculos(int iden, String placa, int quilometragem,double renavam, 
            String status, int precoDeCompra, float anoFabricacao, String observacoes, 
            float capacidade, Modelos modelo, TiposDeVeiculos tiposDeVeiculos) {
        this.iden = iden;
        this.placa = placa;
        this.quilimetragem = quilometragem;
        this.renavam = renavam;
        this.status = status;
        this.precoDeCompra = precoDeCompra;
        this.anoFabricacao = anoFabricacao;
        this.observacoes = observacoes;
        this.capacidade = capacidade;
        this.modelo = modelo;
        this.tiposDeVeiculo = tiposDeVeiculos;
        
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

    public double getRenavam() {
        return renavam;
    }

    public String getStatus() {
        return status;
    }

    public int getPrecoDeCompra() {
        return precoDeCompra;
    }
    
    public float getAnoFabricacao() {
        return anoFabricacao;
    }

    public String getObservacoes() {
        return observacoes;
    }

    public float getCapacidade() {
        return capacidade;
    }
    
    public Modelos getModelo() {
        return modelo;
    }
    
    public TiposDeVeiculos getTiposDeVeiculo() {
        return tiposDeVeiculo;
    }
    //--- FIM GET -------------------------------------------------------------------------------------|
    //

    //--- SET ----------------------------------------------------------------------------------------->
    //
    public void setIden(int iden) {
        this.iden = iden;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public void setQuilimetragem(int quilimetragem) {
        this.quilimetragem = quilimetragem;
    }

    public void setRenavam(double renavam) {
        this.renavam = renavam;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setPrecoDeCompra(int precoDeCompra) {
        this.precoDeCompra = precoDeCompra;
    }
    
    public void setAnoFabricacao(float anoFabricacao) {
        this.anoFabricacao = anoFabricacao;
    }

    public void setObservacoes(String observacoes) {
        this.observacoes = observacoes;
    }

    public void setCapacidade(float capacidade) {
        this.capacidade = capacidade;
    }
    
    public void setModelo(Modelos modelo) {
        this.modelo = modelo;
    }
    
    public void setTiposDeVeiculo(TiposDeVeiculos tiposDeVeiculo) {
        this.tiposDeVeiculo = tiposDeVeiculo;
    }
    //--- FIM SET -------------------------------------------------------------------------------------|
    //
}
