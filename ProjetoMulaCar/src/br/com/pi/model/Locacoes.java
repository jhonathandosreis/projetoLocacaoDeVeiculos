/*
 *  -------------------------------------------------------------------------------------------------->
 *  Licença    : MIT - Copyright 2019 Jhonathan, Gustavo e Miguel 
 *  Criado em  : 21/11/2020 05:12:46 
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

import java.util.Date;

/**
 *
 * @author jhonlinux
 */
public class Locacoes {

    //--- ATRIBUTOS ----------------------------------------------------------------------------------->
    //
    private int iden = 0;
    private Clientes cliente = null;
    private Motoristas motoristas = null;
    private Veiculos veiculos = null;
    private Date dataDeLocacao = null;
    private Date dataPrevistDeDevolucao = null;
    private double kmInicial = 0;
    private float valorLocacao = 0;
    private float valorCaucao = 0;
    private float valorSeguro = 0;
    private float valorTotalPago = 0;
    private SituacaoLocacao situacao = null;
    
    //--- FIM ATRIBUTOS -------------------------------------------------------------------------------|
    //

    //--- CONSTRUTORES -------------------------------------------------------------------------------->
    //
    public Locacoes() {
        
    }
    
    public Locacoes(int iden, Clientes clientes, Motoristas motoristas, Veiculos veiculos,
            Date dataDeLocao, Date dataPrevistaDevolucao, double kmInicial, SituacaoLocacao situacao,
            float valorLocacao, float valorCaucao, float valorSeguro, float valorTotalPago) {
        this.iden = iden;
        this.cliente = clientes;
        this.motoristas = motoristas;
        this.veiculos = veiculos;
        this.dataDeLocacao = dataDeLocao;
        this.dataPrevistDeDevolucao = dataPrevistaDevolucao;
        this.kmInicial = kmInicial;
        this.valorLocacao = valorLocacao;
        this.valorCaucao = valorCaucao;
        this.valorSeguro = valorSeguro;
        this.valorTotalPago = valorTotalPago;
        this.situacao = situacao;
    }
    //--- FIM CONSTRUTORES ----------------------------------------------------------------------------|
    //

    //--- GET ----------------------------------------------------------------------------------------->
    //
    public int getIden() {
        return iden;
    }

    public Clientes getCliente() {
        return cliente;
    }

    public Motoristas getMotoristas() {
        return motoristas;
    }

 

    public Veiculos getVeiculos() {
        return veiculos;
    }

    public Date getDataDeLocacao() {
        return dataDeLocacao;
    }

    public Date getDataPrevistDeDevolucao() {
        return dataPrevistDeDevolucao;
    }

    public double getKmInicial() {
        return kmInicial;
    }
    
    public float getValorLocacao() {
        return valorLocacao;
    }

    public float getValorCaucao() {
        return valorCaucao;
    }

    public float getValorSeguro() {
        return valorSeguro;
    }

    public float getValorTotalPago() {
        return valorTotalPago;
    }
    
    public SituacaoLocacao getSituacao() {
        return situacao;
    }
    //--- FIM GET -------------------------------------------------------------------------------------|
    //

    //--- SET ----------------------------------------------------------------------------------------->
    //
    public void setIden(int iden) {
        this.iden = iden;
    }

    public void setCliente(Clientes cliente) {
        this.cliente = cliente;
    }

    public void setMotoristas(Motoristas motoristas) {
        this.motoristas = motoristas;
    }

 

    public void setVeiculos(Veiculos veiculos) {
        this.veiculos = veiculos;
    }

    public void setDataDeLocacao(Date dataDeLocacao) {
        this.dataDeLocacao = dataDeLocacao;
    }

    public void setDataPrevistDeDevolucao(Date dataPrevistDeDevolucao) {
        this.dataPrevistDeDevolucao = dataPrevistDeDevolucao;
    }

    public void setKmInicial(double kmInicial) {
        this.kmInicial = kmInicial;
    }
    
    public void setValorLocacao(float valorLocacao) {
        this.valorLocacao = valorLocacao;
    }

    public void setValorCaucao(float valorCaucao) {
        this.valorCaucao = valorCaucao;
    }

    public void setValorSeguro(float valorSeguro) {
        this.valorSeguro = valorSeguro;
    }

    public void setValorTotalPago(float valorTotalPago) {
        this.valorTotalPago = valorTotalPago;
    }
    
    public void setSituacao(SituacaoLocacao situacao) {
        this.situacao = situacao;
    }

    //--- FIM SET -------------------------------------------------------------------------------------|
    //    
}
