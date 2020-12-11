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
    private double valorLocacao = 0;
    private double valorCaucao = 0;
    private double valorSeguro = 0;
    private double valorTotalPago = 0;
    private String status = "";
    
    //--- FIM ATRIBUTOS -------------------------------------------------------------------------------|
    //

    //--- CONSTRUTORES -------------------------------------------------------------------------------->
    //
    public Locacoes() {
        
    }
    
    public Locacoes(int iden, Clientes clientes, Motoristas motoristas, Veiculos veiculos,
            Date dataDeLocao, Date dataPrevistaDevolucao, double kmInicial,String status,
            double valorLocacao, double valorCaucao, double valorSeguro, double valorTotalPago) {
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
        this.status = status;
       
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
    
    public double getValorLocacao() {
        return valorLocacao;
    }

    public double getValorCaucao() {
        return valorCaucao;
    }

    public double getValorSeguro() {
        return valorSeguro;
    }

    public double getValorTotalPago() {
        return valorTotalPago;
    }
    
    public String getStatus() {
        return status;
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
    
    public void setValorLocacao(double valorLocacao) {
        this.valorLocacao = valorLocacao;
    }

    public void setValorCaucao(double valorCaucao) {
        this.valorCaucao = valorCaucao;
    }

    public void setValorSeguro(double valorSeguro) {
        this.valorSeguro = valorSeguro;
    }

    public void setValorTotalPago(double valorTotalPago) {
        this.valorTotalPago = valorTotalPago;
    }
    
    public void setStatus(String status) {
        this.status = status;
    }

    //--- FIM SET -------------------------------------------------------------------------------------|
    //    
}
