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
    private double quilometragem = 0;
    private String renavam = "";
    private String status = "";
    private int precoDeCompra = 0;
    private int anoFabricacao = 0;
    private String observacoes = "";
    private int capacidade = 0;
    private String tipoDeCombustivel = "";
    private int capacidadeCombustivel = 0;
    private Modelos modelo = null;
    //--- FIM ATRIBUTOS -------------------------------------------------------------------------------|
    //

    //--- CONSTRUTORES -------------------------------------------------------------------------------->
    //
    public Veiculos() {
        
    }
    
    public Veiculos(int iden, String placa, double quilometragem, String renavam, 
            String status, int precoDeCompra, int anoFabricacao, String observacoes, 
            int capacidade, String tipoDeCombustivel, Modelos modelo, int capacidadeCombustivel) {
        this.iden = iden;
        this.placa = placa;
        this.quilometragem = quilometragem;
        this.renavam = renavam;
        this.status = status;
        this.precoDeCompra = precoDeCompra;
        this.anoFabricacao = anoFabricacao;
        this.observacoes = observacoes;
        this.capacidade = capacidade;
        this.tipoDeCombustivel = tipoDeCombustivel;
        this.modelo = modelo;
        this.capacidadeCombustivel = capacidadeCombustivel;
        
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

    public double getQuilometragem() {
        return quilometragem;
    }

    public String getRenavam() {
        return renavam;
    }

    public String getStatus() {
        return status;
    }

    public int getPrecoDeCompra() {
        return precoDeCompra;
    }
    
    public int getAnoFabricacao() {
        return anoFabricacao;
    }

    public String getObservacoes() {
        return observacoes;
    }

    public int getCapacidade() {
        return capacidade;
    }
    
    public String getTipoDeCombustivel() {
        return tipoDeCombustivel;
    }
    
    public Modelos getModelo() {
        return modelo;
    }
    
    public int getCapacidadeCombustivel() {
        return capacidadeCombustivel;
    }
    //--- FIM GET -------------------------------------------------------------------------------------|
    //

    //--- SET ----------------------------------------------------------------------------------------->
    //
    public void setIden(int iden) {
        this.iden = iden;
    }

    public void setPlaca(String placa) {
        this.placa = placa.trim().toUpperCase();
    }

    public void setQuilometragem(double quilometragem) {
        this.quilometragem = quilometragem;
    }

    public void setRenavam(String renavam) {
        this.renavam = renavam;
    }

    public void setStatus(String status) {
        this.status = status.trim().toUpperCase();
    }

    public void setPrecoDeCompra(int precoDeCompra) {
        this.precoDeCompra = precoDeCompra;
    }
    
    public void setAnoFabricacao(int anoFabricacao) {
        this.anoFabricacao = anoFabricacao;
    }

    public void setObservacoes(String observacoes) {
        this.observacoes = observacoes.trim().toUpperCase();
    }

    public void setCapacidade(int capacidade) {
        this.capacidade = capacidade;
    }
    
    public void setTipoDeCombustivel(String tipoDeCombustivel) {
        this.tipoDeCombustivel = tipoDeCombustivel.trim().toUpperCase();
    }
    
    public void setModelo(Modelos modelo) {
        this.modelo = modelo;
    }

    public void setCapacidadeCombustivel(int capacidadeCombustivel) {
        this.capacidadeCombustivel = capacidadeCombustivel;
    }
    //--- FIM SET -------------------------------------------------------------------------------------|
    //    
}
