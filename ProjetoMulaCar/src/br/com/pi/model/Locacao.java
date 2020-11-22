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

import java.text.DateFormat;

/**
 *
 * @author jhonlinux
 */
public class Locacao {

    //--- ATRIBUTOS ----------------------------------------------------------------------------------->
    //
    private int iden = 0;
    private Clientes cliente = null;
    private Motoristas motoristas = null;
    private Categorias categorias = null;
    private Veiculos veiculos = null;
    private DateFormat dataDeLocacao = null;
    private DateFormat dataPrevistDeDevolucao = null;
    private int kmInicial = 0;
    private int valorLocacao = 0;
    private int valorCaucao = 0;
    private int valorSeguro = 0;
    private int valorTotalPago = 0;
    private SituacaoLocacao situacao = null;
    
    //--- FIM ATRIBUTOS -------------------------------------------------------------------------------|
    //

    //--- CONSTRUTORES -------------------------------------------------------------------------------->
    //
    public Locacao() {
        
    }
    
    public Locacao(int iden, Clientes clientes, Motoristas motoristas, Categorias categorias, Veiculos veiculos,
            DateFormat dataDeLocao, DateFormat dataPrevistaDevolucao, int kmInicial, SituacaoLocacao situacao,
            int valorLocacao, int valorCaucao, int valorSeguro, int valorTotalPago) {
        this.iden = iden;
        this.cliente = clientes;
        this.motoristas = motoristas;
        this.categorias = categorias;
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

    public Categorias getCategorias() {
        return categorias;
    }

    public Veiculos getVeiculos() {
        return veiculos;
    }

    public DateFormat getDataDeLocacao() {
        return dataDeLocacao;
    }

    public DateFormat getDataPrevistDeDevolucao() {
        return dataPrevistDeDevolucao;
    }

    public int getKmInicial() {
        return kmInicial;
    }
    
    public int getValorLocacao() {
        return valorLocacao;
    }

    public int getValorCaucao() {
        return valorCaucao;
    }

    public int getValorSeguro() {
        return valorSeguro;
    }

    public int getValorTotalPago() {
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

    public void setCategorias(Categorias categorias) {
        this.categorias = categorias;
    }

    public void setVeiculos(Veiculos veiculos) {
        this.veiculos = veiculos;
    }

    public void setDataDeLocacao(DateFormat dataDeLocacao) {
        this.dataDeLocacao = dataDeLocacao;
    }

    public void setDataPrevistDeDevolucao(DateFormat dataPrevistDeDevolucao) {
        this.dataPrevistDeDevolucao = dataPrevistDeDevolucao;
    }

    public void setKmInicial(int kmInicial) {
        this.kmInicial = kmInicial;
    }
    
    public void setValorLocacao(int valorLocacao) {
        this.valorLocacao = valorLocacao;
    }

    public void setValorCaucao(int valorCaucao) {
        this.valorCaucao = valorCaucao;
    }

    public void setValorSeguro(int valorSeguro) {
        this.valorSeguro = valorSeguro;
    }

    public void setValorTotalPago(int valorTotalPago) {
        this.valorTotalPago = valorTotalPago;
    }
    
    public void setSituacao(SituacaoLocacao situacao) {
        this.situacao = situacao;
    }

    //--- FIM SET -------------------------------------------------------------------------------------|
    //    
}
